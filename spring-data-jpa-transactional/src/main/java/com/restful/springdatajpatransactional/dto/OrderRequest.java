package com.restful.springdatajpatransactional.dto;

import com.restful.springdatajpatransactional.entity.Order;
import com.restful.springdatajpatransactional.entity.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

    private Order order;
    private Payment payment;
}
