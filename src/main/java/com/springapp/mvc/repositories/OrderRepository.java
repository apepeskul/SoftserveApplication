package com.springapp.mvc.repositories;

import com.springapp.mvc.model.Order;
import com.springapp.mvc.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List <Order> findByCustomerId (User customerId);

    Page<Order> findAll(Pageable pageable);

    Page<Order> findByOrderNumberContaining(Long orderNumber, Pageable pageable);

}
