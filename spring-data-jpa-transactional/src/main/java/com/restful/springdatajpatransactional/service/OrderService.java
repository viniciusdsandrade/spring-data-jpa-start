package com.restful.springdatajpatransactional.service;

import com.restful.springdatajpatransactional.dto.OrderRequest;
import com.restful.springdatajpatransactional.dto.OrderResponse;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest orderRequest);
}