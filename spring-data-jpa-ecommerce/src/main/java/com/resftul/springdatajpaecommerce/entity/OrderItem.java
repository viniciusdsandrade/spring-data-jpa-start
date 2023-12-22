package com.resftul.springdatajpaecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "OrderItem")
@Table(name = "tb_order_items",
        schema = "db_ecommerce")
public class OrderItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String imageUrl;
    private BigDecimal unitPrice;
    private int quantity;
    
    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
    
    @Override
    public String toString() {
        return "{\n" +
                "  \"id\": " + this.id + ",\n" +
                "  \"imageUrl\": \"" + this.imageUrl + "\",\n" +
                "  \"unitPrice\": " + this.unitPrice + ",\n" +
                "  \"quantity\": " + this.quantity + ",\n" +
                "  \"product\": " + this.product + ",\n" +
                "  \"order\": " + this.order + "\n" +
                "}";
    }
}