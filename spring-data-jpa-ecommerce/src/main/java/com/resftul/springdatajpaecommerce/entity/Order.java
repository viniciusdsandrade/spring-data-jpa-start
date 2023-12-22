package com.resftul.springdatajpaecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Order")
@Table(name = "tb_orders",
        schema = "db_ecommerce")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderTrackingNumber;
    private int totalQuantity;
    private BigDecimal totalAmount;
    private String status;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Address billingAddress;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    @Setter(AccessLevel.NONE)
    private Set<OrderItem> orderItems = new HashSet<>();

    public BigDecimal getTotalAmount() {
        BigDecimal amount = BigDecimal.ZERO;
        for(OrderItem item: this.orderItems){
            amount = amount.add(item.getUnitPrice());
        }
        return amount;
    }

    @Override
    public String toString() {
        return "{\n" +
                "  \"id\": " + this.id + ",\n" +
                "  \"orderTrackingNumber\": \"" + this.orderTrackingNumber + "\",\n" +
                "  \"totalQuantity\": " + this.totalQuantity + ",\n" +
                "  \"totalAmount\": " + this.totalAmount + ",\n" +
                "  \"status\": \"" + this.status + "\",\n" +
                "  \"dateCreated\": \"" + this.dateCreated + "\",\n" +
                "  \"lastUpdated\": \"" + this.lastUpdated + "\"\n" +
                "  \"billingAddress\": " + this.billingAddress + "\n" +
                "}";
    }
}