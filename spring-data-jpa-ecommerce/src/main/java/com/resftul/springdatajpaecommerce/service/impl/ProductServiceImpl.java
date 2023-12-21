package com.resftul.springdatajpaecommerce.service.impl;

import com.resftul.springdatajpaecommerce.repository.ProductRepository;
import com.resftul.springdatajpaecommerce.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
