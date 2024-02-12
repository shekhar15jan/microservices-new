package com.hobytech.productservice.repository;

import com.hobytech.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findProductByNameAndPrice(String name, BigDecimal price);
    List<Product> findProductByName(String name);
    List<Product> findProductByPrice(BigDecimal price);
}
