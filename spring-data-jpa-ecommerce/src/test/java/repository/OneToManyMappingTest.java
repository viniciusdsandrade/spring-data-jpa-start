package repository;

import com.resftul.springdatajpaecommerce.entity.Address;
import com.resftul.springdatajpaecommerce.entity.Order;
import com.resftul.springdatajpaecommerce.entity.OrderItem;
import com.resftul.springdatajpaecommerce.entity.Product;
import com.resftul.springdatajpaecommerce.repository.AddressRepository;
import com.resftul.springdatajpaecommerce.repository.OrderRepository;
import com.resftul.springdatajpaecommerce.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class OneToManyMappingTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveOrderMethod() {

        Product product1 = Product.builder()
                .sku("6")
                .name("Test Product 1")
                .description("Test Product Description 1")
                .price(new BigDecimal("12.99"))
                .imageUrl("https://www.test.com")
                .active(true)
                .build();

        Product product2 = Product.builder()
                .sku("12")
                .name("Test Product 2")
                .description("Test Product Description 2")
                .price(new BigDecimal("12.99"))
                .imageUrl("https://www.test.com")
                .active(true)
                .build();

        productRepository.saveAll(List.of(product1, product2));

        Order order = new Order();
        order.setOrderTrackingNumber("100ABC");
        order.setStatus("In progress");

        // create order item 1
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setProduct(productRepository.findById(1L).get());
        orderItem1.setQuantity(2);
        orderItem1.setUnitPrice(orderItem1.getProduct().getPrice().multiply(new BigDecimal(2)));
        orderItem1.setImageUrl("image1.png");
        orderItem1.setOrder(order);
        order.getOrderItems().add(orderItem1);

        // create order item 2
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setProduct(productRepository.findById(2L).get());
        orderItem2.setQuantity(3);
        orderItem2.setUnitPrice(orderItem2.getProduct().getPrice().multiply(new BigDecimal(3)));
        orderItem2.setImageUrl("image2.png");
        orderItem2.setOrder(order);
        order.getOrderItems().add(orderItem2);

        order.setTotalAmount(order.getTotalAmount());

        order.setTotalQuantity(2);

        Address address = Address.builder()
                .cep("13082205")
                .logradouro("Rua Orlando de Oliveira")
                .complemento("")
                .bairro("Jardim São Marcos")
                .localidade("Campinas")
                .uf("SP")
                .ibge("123456789")
                .gia("123456789")
                .ddd("19")
                .siafi("123456789")
                .build();

        order.setBillingAddress(address);

        orderRepository.save(order);
        
        System.out.println("Order ID: " + order.getId());
    }

    @Test
    void fetchOrderMethod(){
        Order order = orderRepository.findById(1L).get();
        System.out.println(order.getStatus());
        System.out.println(order);
        for (OrderItem item: order.getOrderItems()){
            System.out.println(item.getProduct().getName());
        }
    }

    @Test
    void deleteOrderMethod(){
        orderRepository.deleteById(1L);
    }
}