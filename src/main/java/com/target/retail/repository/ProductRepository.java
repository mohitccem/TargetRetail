package com.target.retail.repository;

import com.target.retail.model.Product;
//import org.springframework.data.cassandra.repository.CassandraRepository;
//import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    //@Query(value = "select * from Product where id=?0")
    Product findByProductId(int productId);
}
