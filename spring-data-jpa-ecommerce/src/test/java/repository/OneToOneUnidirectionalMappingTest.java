package repository;

import com.resftul.springdatajpaecommerce.entity.Address;
import com.resftul.springdatajpaecommerce.entity.Order;
import com.resftul.springdatajpaecommerce.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class OneToOneUnidirectionalMappingTest {

    @Autowired
    private OrderRepository orderRepository;
    
    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        orderRepository.deleteAll();
    }

    @Test
    void saveOrderMethod() {
        Order order = Order.builder()
                .orderTrackingNumber("123456789")
                .totalQuantity(1)
                .totalAmount(new BigDecimal(1000))
                .build();

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

        Order savedOrder = orderRepository.save(order);

        System.out.println(savedOrder);
    }
    
    @Test
    void getOrderMethod(){
        Order order = Order.builder()
                .orderTrackingNumber("123456789")
                .totalQuantity(1)
                .totalAmount(new BigDecimal(1000))
                .build();

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
        Order savedOrder = orderRepository.save(order);
        System.out.println(savedOrder);
        
        Order getOrder = orderRepository.findById(savedOrder.getId()).orElse(null);
        System.out.println(getOrder);
    }

    @Test
    void updateOrderWithSaveMethod() {

        Order order = Order.builder()
                .orderTrackingNumber("123456789")
                .totalQuantity(1)
                .totalAmount(new BigDecimal(1000))
                .build();

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
        Order savedOrder = orderRepository.save(order);
        System.out.println(savedOrder);
        
        savedOrder.setTotalAmount(new BigDecimal("2000,00"));
        orderRepository.save(savedOrder);
        System.out.println(savedOrder);
    }

    @Test
    void deleteOrderMethod() {
        // Create and save an order
        Order order = Order.builder()
                .orderTrackingNumber("123456789")
                .totalQuantity(1)
                .totalAmount(new BigDecimal(1000))
                .build();

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
        Order savedOrder = orderRepository.save(order);
        System.out.println("Saved Order: " + savedOrder);

        // Delete the order
        orderRepository.delete(savedOrder);

        // Confirm if the order was deleted
        Order deletedOrder = orderRepository.findById(savedOrder.getId()).orElse(null);
        assertNull(deletedOrder, "The order should be deleted");
    }

}
