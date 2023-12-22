package repository;

import com.resftul.springdatajpaecommerce.entity.Role;
import com.resftul.springdatajpaecommerce.entity.User;
import com.resftul.springdatajpaecommerce.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ManyToManyUnidirectionalTest {
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void testManyToManyUnidirectional() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("vincius_andrade2010@hotmail.com");
        
        Role role = new Role();
        role.setName("ADMIN");
        
        Role role2 = new Role();
        role2.setName("USER");
        
        user.getRoles().add(role);
        user.getRoles().add(role2);
        
        userRepository.save(user);
        
        System.out.println("User saved: " + user);
    }
}