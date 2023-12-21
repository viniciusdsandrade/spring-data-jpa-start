package com.resftul.springdatajpaecommerce.repository;

import com.resftul.springdatajpaecommerce.entity.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class NativeSQLQueriesTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();

        Product product1 = Product.builder()
                .sku("SKU001")
                .name("Laptop Dell XPS 13")
                .description("Powerful and compact laptop for productivity")
                .price(new BigDecimal("1299.99"))
                .imageUrl("laptop_xps13.jpg")
                .active(true)
                .dataCreated(LocalDateTime.parse("2023-01-01T12:00:00"))
                .lastUpdated(LocalDateTime.parse("2023-01-01T12:30:00"))
                .build();

        Product product2 = Product.builder()
                .sku("SKU002")
                .name("Smartphone iPhone 13")
                .description("Latest iPhone with advanced features")
                .price(new BigDecimal("999.99"))
                .imageUrl("iphone13.jpg")
                .active(true)
                .dataCreated(LocalDateTime.parse("2023-01-02T13:00:00"))
                .lastUpdated(LocalDateTime.parse("2023-01-02T13:45:00"))
                .build();


        Product product10 = Product.builder()
                .sku("SKU010")
                .name("Electric Toothbrush Philips Sonicare DiamondClean")
                .description("Achieve cleaner and healthier teeth with advanced sonic technology")
                .price(new BigDecimal("129.99"))
                .imageUrl("sonicare_diamondclean.jpg")
                .active(false)
                .dataCreated(LocalDateTime.parse("2023-01-10T12:00:00"))
                .lastUpdated(LocalDateTime.parse("2023-01-10T12:30:00"))
                .build();

        Product product3 = Product.builder()
                .sku("SKU003")
                .name("4K Ultra HD Smart TV")
                .description("Immersive entertainment experience at home")
                .price(new BigDecimal("899.99"))
                .imageUrl("smart_tv_4k.jpg")
                .active(false)
                .dataCreated(LocalDateTime.parse("2023-01-03T14:30:00"))
                .lastUpdated(LocalDateTime.parse("2023-01-03T15:15:00"))
                .build();

        Product product4 = Product.builder()
                .sku("SKU004")
                .name("Wireless Noise-Canceling Headphones")
                .description("Premium headphones for an enhanced audio experience")
                .price(new BigDecimal("249.99"))
                .imageUrl("headphones_wireless.jpg")
                .active(true)
                .dataCreated(LocalDateTime.parse("2023-01-04T16:00:00"))
                .lastUpdated(LocalDateTime.parse("2023-01-04T16:45:00"))
                .build();

        Product product5 = Product.builder()
                .sku("SKU005")
                .name("Digital Camera Sony Alpha A7 III")
                .description("Professional-grade mirrorless camera for photographers")
                .price(new BigDecimal("1999.99"))
                .imageUrl("sony_alpha_a7iii.jpg")
                .active(false)
                .dataCreated(LocalDateTime.parse("2023-01-05T17:30:00"))
                .lastUpdated(LocalDateTime.parse("2023-01-05T18:15:00"))
                .build();

        Product product6 = Product.builder()
                .sku("SKU006")
                .name("Fitness Tracker Fitbit Charge 5")
                .description("Monitor your health and fitness with advanced tracking features")
                .price(new BigDecimal("149.99"))
                .imageUrl("fitbit_charge5.jpg")
                .active(true)
                .dataCreated(LocalDateTime.parse("2023-01-06T19:00:00"))
                .lastUpdated(LocalDateTime.parse("2023-01-06T19:45:00"))
                .build();

        Product product7 = Product.builder()
                .sku("SKU007")
                .name("Coffee Maker Nespresso VertuoPlus")
                .description("Brew your favorite coffee with this stylish and efficient coffee maker")
                .price(new BigDecimal("179.99"))
                .imageUrl("nespresso_vertuoplus.jpg")
                .active(true)
                .dataCreated(LocalDateTime.parse("2023-01-07T20:30:00"))
                .lastUpdated(LocalDateTime.parse("2023-01-07T21:15:00"))
                .build();

        Product product8 = Product.builder()
                .sku("SKU008")
                .name("Gaming Console PlayStation 5")
                .description("Experience next-gen gaming with the powerful PS5 console")
                .price(new BigDecimal("499.99"))
                .imageUrl("playstation_5.jpg")
                .active(false)
                .dataCreated(LocalDateTime.parse("2023-01-08T22:00:00"))
                .lastUpdated(LocalDateTime.parse("2023-01-08T22:45:00"))
                .build();

        Product product9 = Product.builder()
                .sku("SKU009")
                .name("Smart Thermostat Nest Learning Thermostat")
                .description("Save energy with this intelligent and programmable thermostat")
                .price(new BigDecimal("199.99"))
                .imageUrl("nest_learning_thermostat.jpg")
                .active(true)
                .dataCreated(LocalDateTime.parse("2023-01-09T23:30:00"))
                .lastUpdated(LocalDateTime.parse("2023-01-09T23:59:59"))
                .build();

        List<Product> products = List.of(
                product1, product2, product3,
                product4, product5, product6,
                product7, product8, product9, product10
        );
        productRepository.saveAll(products);
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    void findByNameOrDescriptionNativeIndexParam() {
        List<Product> products = productRepository.findByNameOrDescriptionNativeIndexParam(
                "Smartphone iPhone 13",
                "Achieve cleaner and healthier teeth with advanced sonic technology"
        );
        System.out.println(products);
    }

    @Test
    void findByNameOrDescriptionNativeNamedParam() {
        List<Product> products = productRepository.findByNameOrDescriptionNativeNamedParam(
                "Smartphone iPhone 13",
                "Achieve cleaner and healthier teeth with advanced sonic technology"
        );
        System.out.println(products);
    }
}