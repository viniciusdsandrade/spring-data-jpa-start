package com.restful.springdatajpatransactional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class SpringDataJpaTransactionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaTransactionalApplication.class, args);
	}

}
