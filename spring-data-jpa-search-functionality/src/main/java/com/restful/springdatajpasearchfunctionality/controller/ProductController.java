package com.restful.springdatajpasearchfunctionality.controller;

import com.restful.springdatajpasearchfunctionality.Service.ProductService;
import com.restful.springdatajpasearchfunctionality.entity.Product;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PostMapping("/all")
    public ResponseEntity<List<Product>> createAllProducts(@Valid @RequestBody List<Product> products) {
        List<Product> savedProducts = productService.saveAllProducts(products);
        return new ResponseEntity<>(savedProducts, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("query") String query) {
        return ResponseEntity.ok(productService.searchProducts(query));
    }

    @GetMapping("/sql")
    public ResponseEntity<List<Product>> searchProductsSQL(@RequestParam("query") String query) {
        return ResponseEntity.ok(productService.searchProductsSQL(query));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/delete")
    public ResponseEntity<String> deleteProduct(Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.NO_CONTENT);
    }
}
