package com.hobytech.productservice.service;

import com.hobytech.productservice.dto.ProductRequest;
import com.hobytech.productservice.dto.ProductResponse;
import com.hobytech.productservice.model.Product;
import com.hobytech.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    @Autowired private final ProductRepository productRepository;
    @Override
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getProductName())
                .description(productRequest.getProductDescription())
                .price(productRequest.getProductPrice())
                .build();
        productRepository.insert(product);
    }
    @Override
    public List<ProductResponse> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getId())
                .productName(product.getName())
                .productDescription(product.getDescription())
                .productPrice(product.getPrice())
                .build();
    }

    @Override
    public ProductResponse getProductById(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(IllegalArgumentException::new);
        return ProductResponse.builder()
                .productId(product.getId())
                .productName(product.getName())
                .productDescription(product.getDescription())
                .productPrice(product.getPrice())
                .build();
    }

    @Override
    public List<ProductResponse> getProduct(Map<String, Object> productMap) {
        List<Product> products;
        if(productMap.get("name") != null && productMap.get("price") != null){
            products = productRepository.findProductByNameAndPrice((String) productMap.get("name"), (BigDecimal) productMap.get("price"));
        }else if(productMap.get("name") != null){
            products = productRepository.findProductByName((String) productMap.get("name"));
        }else{
            products = productRepository.findProductByPrice((BigDecimal) productMap.get("price"));
        }
        return products.stream().map(this::mapToProductResponse).toList();
    }

    @Override
    public ProductResponse updateProduct(String productId, ProductRequest productRequest) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Invalid Product Id"));
        product.setName(productRequest.getProductName());
        product.setDescription(productRequest.getProductDescription());
        product.setPrice(productRequest.getProductPrice());
        return mapToProductResponse(productRepository.save(product));
    }

    @Override
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }
}
