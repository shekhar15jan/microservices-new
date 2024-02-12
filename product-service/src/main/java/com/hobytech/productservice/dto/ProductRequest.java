package com.hobytech.productservice.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductRequest {
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
}
