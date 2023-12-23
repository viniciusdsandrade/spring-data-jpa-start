package com.restful.springdatajpasearchfunctionality.Service;

import com.restful.springdatajpasearchfunctionality.entity.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {
    @Transactional(readOnly = true)
    List<Product> searchProducts(String query);
    @Transactional(readOnly = true)
    List<Product> searchProductsSQL(String query);
    @Transactional(readOnly = true)
    List<Product> getAllProducts();
    @Transactional
    List<Product> saveAllProducts(List<Product> products);
    @Transactional
    Product saveProduct(Product product);
    @Transactional
    void deleteProduct(Long id);
}