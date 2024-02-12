package com.hobytech.orderservice.service;

import com.hobytech.orderservice.dto.OrderRequest;

public interface OrderService {
    String placeOrder(OrderRequest orderRequest);
}
