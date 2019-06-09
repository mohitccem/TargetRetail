package com.target.retail.mapper;

import org.springframework.stereotype.Component;

import com.target.retail.dto.PriceDTO;
import com.target.retail.dto.ProductDTO;
import com.target.retail.model.Product;

@Component
public class ProductMapper {

    public ProductDTO mapToDto(Product product) {

        ProductDTO productDTO = null;

        if (product != null) {
            productDTO = new ProductDTO();
            PriceDTO priceDTO = new PriceDTO();

            /**Update the priceDTO first **/
            priceDTO.setPrice(String.valueOf(product.getPrice()));
            priceDTO.setCurrencyCode(product.getCurrency());

            /**Add priceDTO to productDTO**/
            productDTO.setPriceDTO(priceDTO);

            /**Set product id and product name in the productDTO **/
            productDTO.setProductId(product.getProductId());
            productDTO.setProductName(product.getName());
        }

        return productDTO;
    }

    public Product toResponseOutput(ProductDTO productDTO) {

        Product product = new Product(productDTO.getProductId());
        product.setPrice(Double.parseDouble(productDTO.getPriceDTO().getPrice()));

        return product;
    }

}
