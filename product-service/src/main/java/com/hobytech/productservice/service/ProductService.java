package com.hobytech.productservice.service;

import com.hobytech.productservice.dto.ProductRequest;
import com.hobytech.productservice.dto.ProductResponse;

import java.util.List;
import java.util.Map;

public interface ProductService {
    void createProduct(ProductRequest productRequest);

    List<ProductResponse> getProducts();

    ProductResponse getProductById(String productId);

    List<ProductResponse> getProduct(Map<String, Object> productMap);

    ProductResponse updateProduct(String productId, ProductRequest productRequest);

    void deleteProduct(String productId);
}
