package com.restful.springdatajpatransactional.controller;

import com.restful.springdatajpatransactional.dto.OrderRequest;
import com.restful.springdatajpatransactional.dto.OrderResponse;
import com.restful.springdatajpatransactional.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.placeOrder(orderRequest));
    }
}