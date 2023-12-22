package repository;

import com.resftul.springdatajpaecommerce.entity.Address;
import com.resftul.springdatajpaecommerce.entity.Order;
import com.resftul.springdatajpaecommerce.repository.AddressRepository;
import com.resftul.springdatajpaecommerce.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OneToOneBidirectionalMappingTest {

    @Autowired
    private AddressRepository addressRepository;
    
    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        //addressRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        // addressRepository.deleteAll();
    }

    @Test
    void saveAddressMethod() {

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
                .order(order)
                .build();

        order.setBillingAddress(address);
        address.setOrder(order);
        Address savedAddress = addressRepository.save(address);

        System.out.println(savedAddress);
    }

    @Test
    void updateAddressWithSaveMethod() {
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

        Address savedAddress = addressRepository.save(address);

        savedAddress.setBairro("Jardim São Marcos");
        savedAddress.setCep("32461383");
        savedAddress.setLogradouro("Rua João de Oliveira");
        savedAddress.setComplemento("Casa");

        System.out.println(savedAddress);
    }

    @Test
    void updateAddressMethod() {
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

        Order order = Order.builder()
                .id(1L)
                .orderTrackingNumber("123456789")
                .totalQuantity(1)
                .totalAmount(new BigDecimal(1000))
                .build();
        
        Order savedOrder = orderRepository.save(order);
        address.setOrder(savedOrder);

        Address savedAddress = addressRepository.save(address);

        address.setBairro("Jardim São Marcos");
        address.setCep("32461383");
        address.getOrder().setStatus("DELIVERED");

        System.out.println(savedAddress);
    }

    @Test
    void fetchAddressMethod() {
        Address address = addressRepository.findById(1L).get();
        System.out.println(address);

    }

    @Test
    void deleteAddressMethod() {
        addressRepository.deleteById(1L);
    }
}