package com.restful.springdatajpatransactional.repository;

import com.restful.springdatajpatransactional.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


}
