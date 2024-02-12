package com.hobytech.productservice.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductResponse {
    private String productId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
}
