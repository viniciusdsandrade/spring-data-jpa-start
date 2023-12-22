package repository;

import com.resftul.springdatajpaecommerce.entity.Product;
import com.resftul.springdatajpaecommerce.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class PaginationAndSortingTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void pagination() {

        int pageNo = 0;
        int pageSize = 10;

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        
        Page<Product> products = productRepository.findAll(pageable);

        System.out.println("Page size: " + pageable.getPageSize());
        System.out.println("Page number: " + pageable.getPageNumber());
        System.out.println("Offset: " + pageable.getOffset());
        System.out.println("Sort: " + pageable.getSort());
        System.out.println("isFirst: " + pageable.first());
        System.out.println("isPaged: " + pageable.isPaged());

        products.forEach(System.out::println);
    }
    
    @Test
    void sorting() {

        int pageNo = 0;
        int pageSize = 10;

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        
        String sortByPrice = "price";
        String sortDir = "desc";
        
        Sort sort = sortDir.equals("asc") ? Sort.by(sortByPrice).ascending() : Sort.by(sortByPrice).descending();
        
        List<Product> products = productRepository.findAll(sort);
        
        products.forEach(System.out::println);
    }
    
    @Test
    void  sortingByMultipleFields(){
            
            int pageNo = 0;
            int pageSize = 10;
    
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            
            String sortByPrice = "price";
            String sortBySku = "sku";
            String sortDir = "desc";
            
            Sort sort = sortDir.equals("asc") ? Sort.by(sortByPrice).ascending().and(Sort.by(sortBySku).ascending()) : Sort.by(sortByPrice).descending().and(Sort.by(sortBySku).descending());
            
            List<Product> products = productRepository.findAll(sort);
            Page<Product> productsPage = productRepository.findAll(pageable);
            
            products.forEach(System.out::println);
            productsPage.forEach(System.out::println);
    }

    @Test
    void paginationAndSorting() {
        int pageNo = 0;
        int pageSize = 10;

        String sortByPrice = "price";
        String sortDir = "desc";

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortDir.equals("asc") ? Sort.Order.asc(sortByPrice) : Sort.Order.desc(sortByPrice)));

        Page<Product> productsPage = productRepository.findAll(pageable);

        // Assuming you have a method to print information about products
        printProductPageInfo(productsPage);
    }

    // Additional method to print information about products
    private void printProductPageInfo(Page<Product> productsPage) {
        System.out.println("Total Elements: " + productsPage.getTotalElements());
        System.out.println("Total Pages: " + productsPage.getTotalPages());
        System.out.println("Current Page Number: " + productsPage.getNumber());
        System.out.println("Page Size: " + productsPage.getSize());
        System.out.println("Sort: " + productsPage.getSort());

        List<Product> products = productsPage.getContent();
        products.forEach(System.out::println);
    }
    
}