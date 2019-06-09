package com.target.retail.service;

import com.target.retail.dto.ProductDTO;
import com.target.retail.model.Product;

public interface ProductService {

    ProductDTO getProduct(int id);

    ProductDTO updatePrice(Product prod);

}