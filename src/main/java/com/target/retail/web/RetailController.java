package com.target.retail.web;

import static org.springframework.http.HttpStatus.RESET_CONTENT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.target.retail.dto.ProductDTO;
import com.target.retail.model.Product;
import com.target.retail.mapper.ProductMapper;
import com.target.retail.service.ProductService;

@RestController
@RequestMapping("/retail")
public class RetailController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    /*Deliver product details as a json*/
    @RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<ProductDTO> getProduct(@PathVariable int productId) {
        ProductDTO product = productService.getProduct(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /*Update the product price in datastore*/
    @RequestMapping(value = "/products/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(RESET_CONTENT)
    public void updateProductPrice(@RequestBody ProductDTO inputDTO,
                                   @PathVariable int productId) {
        Product product = productMapper.toResponseOutput(inputDTO);
        productService.updatePrice(product);
    }

}