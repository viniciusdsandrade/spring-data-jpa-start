package com.restful.springdatajpatransactional.service.impl;

import com.restful.springdatajpatransactional.dto.OrderRequest;
import com.restful.springdatajpatransactional.dto.OrderResponse;
import com.restful.springdatajpatransactional.entity.Order;
import com.restful.springdatajpatransactional.entity.Payment;
import com.restful.springdatajpatransactional.exception.PaymentException;
import com.restful.springdatajpatransactional.repository.OrderRepository;
import com.restful.springdatajpatransactional.repository.PaymentRepository;
import com.restful.springdatajpatransactional.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public OrderResponse placeOrder(OrderRequest orderRequest) {

        Order order = orderRequest.getOrder();
        order.setStatus("IN_PROGRESS");
        Order savedOrder = orderRepository.save(order);

        Payment payment = orderRequest.getPayment();

        if(!payment.getType().equals("DEBIT")) {
            throw new PaymentException("Invalid payment type");
        }

        payment.setOrderId(savedOrder.getId());
        paymentRepository.save(payment);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderTrackingNumber(UUID.randomUUID().toString());
        orderResponse.setStatus(savedOrder.getStatus());
        orderResponse.setMessage("Order placed successfully");

        return orderResponse;
    }
}