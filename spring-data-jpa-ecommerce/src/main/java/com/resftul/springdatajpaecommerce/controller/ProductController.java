package com.resftul.springdatajpaecommerce.controller;

import com.resftul.springdatajpaecommerce.service.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
}
