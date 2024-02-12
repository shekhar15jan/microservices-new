package com.hobytech.productservice.controller;

import com.hobytech.productservice.dto.ProductRequest;
import com.hobytech.productservice.dto.ProductResponse;
import com.hobytech.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {
    @Autowired private final ProductService productService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<ProductResponse> getProducts(){
        return productService.getProducts();
    }
    @GetMapping("/{product-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ProductResponse getProductById(@PathVariable(name = "product-id") String productId){
        return productService.getProductById(productId);
    }
    @GetMapping("/params")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<ProductResponse> getProduct(
            @RequestParam(name="name", required = false) String name,
            @RequestParam(name="price", required = false) BigDecimal price){
        if(name == null && price == null){
            throw new IllegalStateException("Invalid Arguments");
        }

        Map<String, Object> productMap = new HashMap<>();
        productMap.put("name",name);
        productMap.put("price", price);
        return productService.getProduct(productMap);
    }

    @PutMapping("/{product-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ProductResponse updateProduct(@PathVariable(name = "product-id") String productId, @RequestBody ProductRequest productRequest){
        return productService.updateProduct(productId, productRequest);
    }

    @DeleteMapping("/{product-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteProduct(@PathVariable(name="product-id") String productId){
        productService.deleteProduct(productId);
    }
}
