package com.resftul.springdatajpaecommerce.repository;

import com.resftul.springdatajpaecommerce.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QueryMethodsTest {

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
    
    @Test
    void findTop2ByOrderByPriceDesc() {
        List<Product> top2Products = productRepository.findTop2ByOrderByPriceDesc();
        assertEquals(2, top2Products.size()); // Assuming you want the top 2 products
        top2Products.forEach(System.out::println);
    }
    
    @Test
    void testFindByNameIn() {
        // Dado
        List<String> names = List.of("Laptop Dell XPS 13", "Smartphone iPhone 13");

        // Quando
        List<Product> products = productRepository.findByNameIn(names);

        // Então
        assertFalse(products.isEmpty(), "Should find products by specified names");
        products.forEach(product -> assertTrue(names.contains(product.getName()),
                "Product name should be one of the specified names"));
    }

    @Test
    void testFindByNameContaining() {
        // Dado
        String searchTerm = "camera";

        // Quando
        List<Product> products = productRepository.findByNameContaining(searchTerm);

        // Então
        assertFalse(products.isEmpty(), "Should find products containing the specified term");
        products.forEach(product -> assertTrue(product.getName().toLowerCase().contains(searchTerm.toLowerCase()),
                "Product name should contain the search term"));
    }

    @Test
    void testFindByNameLike() {
        // Dado
        String searchTerm = "sony%";

        // Quando
        List<Product> products = productRepository.findByNameLike(searchTerm);

        // Então
        assertTrue(products.isEmpty(), "Should find products matching the specified pattern");
    }
    
    @Test
    void findDistinctByName() {
        String productName = "Smartphone iPhone 13";
        Product product = productRepository.findDistinctByName(productName);

        System.out.println("Distinct product with name '" + productName + "':");
        System.out.println(product);
    }


    @Test
    void findByNameIgnoreCase() {
        Product product = productRepository.findByNameIgnoreCase("Dell");
        System.out.println(product);
    }

    @Test
    void findByNameAndDescriptionAllIgnoreCase() {
        Product product = productRepository.findByNameOrDescriptionAllIgnoreCase("dell", "powerful");
        System.out.println(product);
    }

    @Test
    void findByPriceGreaterThan() {
        List<Product> products = productRepository.findByPriceGreaterThan(new BigDecimal("1000"));
        products.forEach(System.out::println);
    }

    @Test
    void findByPriceBetween() {
        List<Product> products = productRepository.findByPriceBetween(new BigDecimal("1000"), new BigDecimal("2000"));
        products.forEach(System.out::println);
    }

    @Test
    void findPriceByLessThanEqual() {
        List<Product> products = productRepository.findByPriceLessThanEqual(new BigDecimal("600"));
        products.forEach(System.out::println);
    }

    @Test
    void findByActiveTrue() {
        List<Product> products = productRepository.findByActiveTrue();
        products.forEach(System.out::println);
    }

    @Test
    void findByPriceLessThan() {
        List<Product> products = productRepository.findByPriceLessThan(new BigDecimal("1000"));
        System.out.println("Products with price less than 1000:");
        products.forEach(System.out::println);
    }

    @Test
    void findByPriceGreaterThanEqual() {
        List<Product> products = productRepository.findByPriceGreaterThanEqual(new BigDecimal("1000"));
        System.out.println("Products with price greater than or equal to 1000:");
        products.forEach(System.out::println);
    }

    @Test
    void findByPriceBetweenAndActiveTrue() {
        List<Product> products = productRepository.findByPriceBetweenAndActiveTrue(new BigDecimal("500"), new BigDecimal("1500"));
        System.out.println("Active products with price between 500 and 1500:");
        products.forEach(System.out::println);
    }

    @Test
    void findByNameStartingWithIgnoreCase() {
        List<Product> products = productRepository.findByNameStartingWithIgnoreCase("Laptop");
        System.out.println("Products with name starting with 'Laptop':");
        products.forEach(System.out::println);
    }

    @Test
    void findByNameEndingWithIgnoreCase() {
        List<Product> products = productRepository.findByNameEndingWithIgnoreCase("Thermostat");
        System.out.println("Products with name ending with 'Thermostat':");
        products.forEach(System.out::println);
    }

    @Test
    void findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase() {
        List<Product> products = productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase("Smart", "camera");
        System.out.println("Products with name containing 'Smart' or description containing 'camera':");
        products.forEach(System.out::println);
    }

    @Test
    void findByPriceAndNameNotLike() {
        List<Product> products = productRepository.findByPriceAndNameNotLike(new BigDecimal("100"), "%Smart%");
        System.out.println("Products with price 100 and name not like 'Smart':");
        products.forEach(System.out::println);
    }

    @Test
    void findByDescriptionNotContaining() {
        List<Product> products = productRepository.findByDescriptionNotContaining("premium");
        System.out.println("Products with description not containing 'premium':");
        products.forEach(System.out::println);
    }

    @Test
    void findByDataCreatedAfter() {
        LocalDateTime date = LocalDateTime.parse("2023-01-05T00:00:00");
        List<Product> products = productRepository.findByDataCreatedAfter(date);
        System.out.println("Products created after 2023-01-05:");
        products.forEach(System.out::println);
    }

    @Test
    void findByLastUpdatedBefore() {
        LocalDateTime date = LocalDateTime.parse("2023-01-08T00:00:00");
        List<Product> products = productRepository.findByLastUpdatedBefore(date);
        System.out.println("Products last updated before 2023-01-08:");
        products.forEach(System.out::println);
    }
}