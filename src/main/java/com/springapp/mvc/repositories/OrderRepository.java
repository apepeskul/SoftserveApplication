package com.springapp.mvc.repositories;

import com.springapp.mvc.model.Order;
import com.springapp.mvc.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: WinJavaEnv
 * Date: 24.11.13
 * Time: 21:25
 * To change this template use File | Settings | File Templates.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    List <Order> findByCustomerId (User customerId);
    Page<Order> findAll(Pageable pageable);
}
