package com.target.retail.service;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.target.retail.dto.ProductDTO;
import com.target.retail.model.Product;
import com.target.retail.exceptions.InvalidRequestException;
import com.target.retail.exceptions.ProductNotFoundException;
import com.target.retail.mapper.ProductMapper;
import com.target.retail.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    private static final String PRODUCT_NAME_PATH = "/product/item/product_description/title";

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    public static final String PRODUCT_PATH = "v2/pdp/tcin/";
    private static final String PRODUCT_NOT_FOUND = "%s";

    @Value("${target.hostname}")
    private String hostName;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO getProduct(int productId) {

        Product product = null;

        /** If the product id is valid , then get the product name from redsky.target.com **/
        String productName = getProductName(productId);

        /** Check the product price in database **/
        product = productRepository.findByProductId(productId);

        /**Throw exception product not found**/
        if (product == null) {
            product = new Product(productId);
        }

        /**Set the product name in the final response**/
        product.setName(productName);

        return productMapper.mapToDto(product);
    }

    private String getProductName(int productId) {

        String productName = null;
        String responseBody = null;
        ResponseEntity<String> response = null;

        java.net.URI url = getURL(productId);

        try {
            response = restTemplate.getForEntity(url, String.class);
            responseBody = response.getBody();
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ProductNotFoundException("product_id:" + productId, ex);
            } else {
                throw new RuntimeException("product_id : " + productId, ex);
            }
        } catch (Exception e) {
            throw new RuntimeException(
                    "Unknown exception occured while fetching name for product id " + productId, e);
        }
        if (!response.getStatusCode().equals(HttpStatus.OK))
            log.error("Error ocurred while fetching product name, status code: " + response.getStatusCode().value());
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode node = mapper.readTree(responseBody);
            JsonNode nameNode = node.at(PRODUCT_NAME_PATH);
            log.info("product_name:{}", nameNode);
            productName = nameNode.textValue();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Exception occured during JSON processing : " + responseBody, e);
        } catch (IOException e) {
            throw new RuntimeException("Exception ocurred while fetching product name :  " + responseBody, e);
        }
        return productName;
    }

    @Override
    public ProductDTO updatePrice(Product product) {
        int productId = product.getProductId();

        Product existingProduct = productRepository.findByProductId(productId);
        if (existingProduct == null) {
            log.info("product_id:{} not found in db", productId);
            throw new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND, productId));
        }
        log.info("existing_price:{} for product_id:{} .", existingProduct.getPrice(), productId);
        existingProduct.setPrice(product.getPrice());
        Product updatedProduct = productRepository.save(existingProduct);
        log.info("new_price:{} for product_id:{} : ", updatedProduct.getPrice(), productId);

        return productMapper.mapToDto(product);
    }

    private java.net.URI getURL(int productId) {

        StringBuilder url = new StringBuilder(hostName);
        url.append(PRODUCT_PATH);
        url.append(productId);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url.toString());

        return builder.build().encode().toUri();
    }


}
