package com.resftul.springdatajpaecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Product")
@Table(name = "tb_product",
        schema = "db_ecommerce",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "sku_unique",
                        columnNames = "stock_keeping_unit"
                )

        }
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stock_keeping_unit", nullable = false)
    private String sku;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean active;
    
    private String imageUrl;

    @CreationTimestamp
    private LocalDateTime dataCreated;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;
    
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory category;

    @Override
    public String toString() {
        return "{\n" +
                "  \"active\": " + this.active + ",\n" +
                "  \"dataCreated\": \"" + this.dataCreated + "\",\n" +
                "  \"description\": \"" + this.description + "\",\n" +
                "  \"id\": " + this.id + ",\n" +
                "  \"imageUrl\": \"" + this.imageUrl + "\",\n" +
                "  \"lastUpdated\": \"" + this.lastUpdated + "\",\n" +
                "  \"name\": \"" + this.name + "\",\n" +
                "  \"price\": " + this.price + ",\n" +
                "  \"sku\": \"" + this.sku + "\"\n" +
                "}";
    }
}