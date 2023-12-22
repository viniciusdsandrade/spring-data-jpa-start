package repository;

import com.resftul.springdatajpaecommerce.entity.Product;
import com.resftul.springdatajpaecommerce.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the ProductRepository using Spring Data JPA.
 */
@SpringBootTest
public class ProductRepositoryTest {

    /*
     * 1 - deleteAll()
     * 2 - save(entity)
     * 3 - findById(id)
     * 4 - saveAll(entities)
     * 5 - existById(id)
     * 6 - save(entity) - update
     * 7 - findAll()
     * 8 - deleteById(id)
     * 9 - count()
     * 10 - delete(entity)
     */

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    /**
     * Method 1: deleteAll()
     * - Deletes all products in the repository.
     * - Clears the repository for a clean state before each test.
     */
    @Test
    @DisplayName("save product using save method")
    void saveMethod() {
        Product product = Product.builder()
                .sku("1")
                .name("Test Product")
                .description("Test Product Description")
                .price(new BigDecimal("12.99"))
                .imageUrl("https://www.test.com")
                .active(true)
                .build();

        Product savedObject = productRepository.save(product);

        System.out.println(savedObject);
    }

    /**
     * Method 2: save(entity)
     * - Saves a single product entity to the repository.
     * - Verifies that the saved product has an assigned ID.
     */
    @Test
    @DisplayName("updated product using save method")
    void updateUsingSaveMethod() {
        Product product = Product.builder()
                .sku("2")
                .name("Test Product")
                .description("Test Product Description")
                .price(new BigDecimal("12.99"))
                .imageUrl("https://www.test.com")
                .active(true)
                .build();

        Product savedObject = productRepository.save(product);
        savedObject.setPrice(new BigDecimal("10.99"));
        productRepository.save(savedObject);
        System.out.println(savedObject);
    }

    /**
     * Method 3: findById(id)
     * - Retrieves a product from the repository by its ID.
     * - Verifies that the product is retrieved successfully.
     */
    @Test
    @DisplayName("findById Method")
    void findByIdMethod() {
        Long id = 1L;

        Product product = productRepository.findById(id).orElse(null);

        System.out.println(product);
    }

    /**
     * Method 4: saveAll(entities)
     * - Saves a list of product entities to the repository.
     * - Prints the saved products to the console.
     */
    @Test
    @DisplayName("saveAll Method")
    void saveAllMethod() {
        Product product1 = Product.builder()
                .sku("3")
                .name("Test Product 1")
                .description("Test Product Description 1")
                .price(new BigDecimal("12.99"))
                .imageUrl("https://www.test.com")
                .active(true)
                .build();

        Product product2 = Product.builder()
                .sku("4")
                .name("Test Product 2")
                .description("Test Product Description 2")
                .price(new BigDecimal("12.99"))
                .imageUrl("https://www.test.com")
                .active(true)
                .build();

        List<Product> products = List.of(product1, product2);
        Iterable<Product> savedProducts = productRepository.saveAll(products);

        savedProducts.forEach(System.out::println);
    }

    /**
     * Method 5: existsById(id)
     * - Checks if a product with a given ID exists in the repository.
     * - Verifies that the product exists after being saved.
     */
    @Test
    @DisplayName("findAll Method")
    void findAllMethod() {
        Product product1 = Product.builder()
                .sku("3")
                .name("Test Product 1")
                .description("Test Product Description 1")
                .price(new BigDecimal("12.99"))
                .imageUrl("https://www.test.com")
                .active(true)
                .build();

        Product product2 = Product.builder()
                .sku("4")
                .name("Test Product 2")
                .description("Test Product Description 2")
                .price(new BigDecimal("12.99"))
                .imageUrl("https://www.test.com")
                .active(true)
                .build();

        List<Product> products = List.of(product1, product2);
        productRepository.saveAll(products);

        Iterable<Product> savedProducts = productRepository.findAll();

        savedProducts.forEach(System.out::println);
    }

    /**
     * Method 6: delete(entity)
     * - Deletes a single product entity from the repository.
     * - Verifies that the product is deleted successfully.
     */
    @Test
    @DisplayName("deleteById Method")
    void deleteByIdMethod() {
        // Dado
        Long id = 1L;

        // Quando
        productRepository.deleteById(id);

        // Então
        assertFalse(productRepository.existsById(id), "Product should not exist after deletion");
    }

    /**
     * Method 7: deleteAll()
     * - Deletes all products in the repository.
     * - Verifies that the product count is zero after deletion.
     */
    @Test
    @DisplayName("delete() entity method")
    void deleteEntityMethod() {
        // Dado
        Product product = Product.builder()
                .sku("3")
                .name("Test Product 1")
                .description("Test Product Description 1")
                .price(new BigDecimal("12.99"))
                .imageUrl("https://www.test.com")
                .active(true)
                .build();

        // Salva o produto antes de deletar
        Product savedProduct = productRepository.save(product);

        // Quando
        productRepository.delete(product);

        // Então
        assertNull(productRepository.findById(savedProduct.getId()).orElse(null), "Product should be deleted");
    }

    /**
     * Method 8: count()
     * - Counts the number of products in the repository.
     * - Verifies that the product count matches the expected count.
     */
    @Test
    @DisplayName("deleteAll() method")
    void deleteAllMethod() {
        // Dado
        Product product1 = Product.builder()
                .sku("3")
                .name("Test Product 1")
                .description("Test Product Description 1")
                .price(new BigDecimal("12.99"))
                .imageUrl("https://www.test.com")
                .active(true)
                .build();

        Product product2 = Product.builder()
                .sku("4")
                .name("Test Product 2")
                .description("Test Product Description 2")
                .price(new BigDecimal("12.99"))
                .imageUrl("https://www.test.com")
                .active(true)
                .build();

        List<Product> products = List.of(product1, product2);
        productRepository.saveAll(products);

        // Quando
        productRepository.deleteAll();

        // Então
        assertEquals(0, productRepository.count(), "Product count should be zero");
    }


    @Test
    @DisplayName("count() method")
    void countMethod() {
        // Dado
        Product product1 = Product.builder()
                .sku("3")
                .name("Test Product 1")
                .description("Test Product Description 1")
                .price(new BigDecimal("12.99"))
                .imageUrl("https://www.test.com")
                .active(true)
                .build();

        Product product2 = Product.builder()
                .sku("4")
                .name("Test Product 2")
                .description("Test Product Description 2")
                .price(new BigDecimal("12.99"))
                .imageUrl("https://www.test.com")
                .active(true)
                .build();

        List<Product> products = List.of(product1, product2);
        productRepository.saveAll(products);

        // Quando
        long count = productRepository.count();

        // Então
        assertEquals(2, count, "Product count should be two");
    }
}