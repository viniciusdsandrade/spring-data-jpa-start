package com.resftul.springdatajpaecommerce.repository;

import com.resftul.springdatajpaecommerce.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByCep(String cep);
}
