package com.restful.springdatajpatransactional.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Payment")
@Table(name = "tb_payment",
        schema = "db_transactional_management")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String cardName;
    private String cardNumber;
    private String expiryDate;
    private String expiryMonth;
    private String cvc;
    private Long orderId;
}