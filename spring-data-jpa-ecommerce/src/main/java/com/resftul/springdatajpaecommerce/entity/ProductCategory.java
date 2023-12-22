package com.resftul.springdatajpaecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ProductCategory")
@Table(name = "tb_product_categories",
        schema = "db_ecommerce")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;
    private String categoryDescription;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "category",
            fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();
}