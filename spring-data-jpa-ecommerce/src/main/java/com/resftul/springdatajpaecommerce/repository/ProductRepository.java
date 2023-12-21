package com.resftul.springdatajpaecommerce.repository;

import com.resftul.springdatajpaecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    /*
    Keywords
    
    1 - Distinct
    2 - And
    3 - Or
    4 - Is, Equal
    5 - Between
    6 - LessThan
    7 - LessThanEqual
    8 - GreaterThan
    9 - GreaterThanEqual
    10 - After
    11 - Before
    12 - IsNull, Null
    13 - IsNotNull, NotNull
    14 - Like
    15 - NotLike
    16 - StartingWith
    17 - EndingWith
    18 - Containing
    19 - OrderBy
    20 - Not
    21 - In
    22 - NotIn
    23 - True
    24 - False
    25 - IgnoreCase
     */
    
    /**
     * Busca uma lista de produtos cujo nome contenha a sequência especificada.
     *<p>
     * Este método realiza uma busca sensível a maiúsculas e minúsculas para recuperar produtos
     * cujo nome contenha a sequência fornecida em qualquer lugar no nome.
     *<p>
     * @param name A sequência a ser buscada nos nomes dos produtos.
     * @return Uma lista de produtos que correspondem à sequência especificada em seus nomes.
     */
    List<Product> findByNameContaining(String name);

    /**
     * Busca uma lista de produtos cujo nome é semelhante à sequência especificada.
     *<p>
     * Este método permite o uso de curingas ('%') para correspondência mais flexível. Realiza uma
     * busca sensível a maiúsculas e minúsculas para recuperar produtos cujo nome é semelhante à
     * sequência fornecida com base no padrão de curinga especificado.
     *<p>
     * @param name A sequência ou padrão de curinga a ser buscado nos nomes dos produtos.
     * @return Uma lista de produtos que correspondem à sequência ou padrão de curinga especificado em seus nomes.
     *
     * @see org.springframework.data.repository.query.QueryByExampleExecutor
     */
    List<Product> findByNameLike(String name);
    
    List<Product> findTop2ByOrderByPriceDesc();
    Product findDistinctByName(String name);
    Product findByNameIgnoreCase(String name);
    Product findByNameOrDescriptionAllIgnoreCase(String name, String description);
    List<Product> findByPriceGreaterThan(BigDecimal price);
    List<Product> findByPriceBetween(BigDecimal price1, BigDecimal price2);
    List<Product> findByPriceLessThanEqual(BigDecimal price);
    List<Product> findByActiveTrue();
    List<Product> findByPriceLessThan(BigDecimal price);
    List<Product> findByPriceGreaterThanEqual(BigDecimal price);
    List<Product> findByPriceBetweenAndActiveTrue(BigDecimal minPrice, BigDecimal maxPrice);
    List<Product> findByNameStartingWithIgnoreCase(String prefix);
    List<Product> findByNameEndingWithIgnoreCase(String suffix);
    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
    List<Product> findByPriceAndNameNotLike(BigDecimal price, String namePattern);
    List<Product> findByDescriptionNotContaining(String keyword);
    List<Product> findByDataCreatedAfter(LocalDateTime date);
    List<Product> findByLastUpdatedBefore(LocalDateTime date);

    /**
     * Find products by a list of names.
     *
     * @param names List of product names to search for.
     * @return List of products matching the specified names.
     */
    List<Product> findByNameIn(List<String> names);


    // Define JPQL query using @Query annotation with index or position parameters
    @Query("select p from Product p where p.name = ?1 or p.description = ?2")
    List<Product> findByNameOrDescriptionJPQLIndexParam(String name, String description);
    
    // Define JPQL query using @Query annotation with named parameters
    @Query("select p from Product p where p.name = :name or p.description = :description")
    List<Product>  findByNameOrDescriptionJPQLNamedParam(
            @Param("name") String name, 
            @Param("description") String description
    );
    
    // Define native SQL query using @Query annotation with index or position parameters
    @Query(value = "SELECT * FROM tb_product WHERE name = ?1 OR description = ?2", nativeQuery = true)
    List<Product> findByNameOrDescriptionNativeIndexParam(String name, String description);
    
    // Define native SQL query using @Query annotation with named parameters
    @Query(value = "SELECT * FROM tb_product WHERE name = :name OR description = :description", nativeQuery = true)
    List<Product> findByNameOrDescriptionNativeNamedParam(
            @Param("name") String name, 
            @Param("description") String description
    );
}