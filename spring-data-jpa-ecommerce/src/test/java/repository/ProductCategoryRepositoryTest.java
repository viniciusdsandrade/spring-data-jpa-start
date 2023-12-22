package repository;

import com.resftul.springdatajpaecommerce.entity.Product;
import com.resftul.springdatajpaecommerce.entity.ProductCategory;
import com.resftul.springdatajpaecommerce.repository.ProductCategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class ProductCategoryRepositoryTest {
    
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    void findAll() {
        ProductCategory productCategory = ProductCategory.builder()
                .categoryName("Books")
                .categoryDescription("Books")
                .build();

        Product product = Product.builder()
                .sku("1")
                .name("Test Product")
                .description("Test Product Description")
                .price(new BigDecimal("12.99"))
                .imageUrl("https://www.test.com")
                .active(true)
                .build();
        
        productCategory.getProducts().add(product);
        
    }
}