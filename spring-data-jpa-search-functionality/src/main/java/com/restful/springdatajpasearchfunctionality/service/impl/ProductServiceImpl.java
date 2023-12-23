package com.restful.springdatajpasearchfunctionality.Service.impl;

import com.restful.springdatajpasearchfunctionality.Service.ProductService;
import com.restful.springdatajpasearchfunctionality.entity.Product;
import com.restful.springdatajpasearchfunctionality.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> searchProducts(String query) {
        return productRepository.searchProducts(query);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> searchProductsSQL(String query) {
        return productRepository.searchProductsSQL(query);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public List<Product> saveAllProducts(List<Product> products) {
        if (products.size() == 1) {
            Optional<Product> firstProduct = products.stream().findFirst();
            firstProduct.ifPresent(productRepository::save);
        } else if (!products.isEmpty()) {
            return productRepository.saveAll(products);
        }
        return List.of();
    }

    @Transactional
    public Product saveProduct(Product product) {

        Product existingProduct = productRepository.findById(product.getId()).orElse(null);

        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            return productRepository.save(existingProduct);
        } else {
            return productRepository.save(product);
        }


    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
