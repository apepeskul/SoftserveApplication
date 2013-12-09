package com.springapp.mvc.repositories;

import com.springapp.mvc.model.OrderDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: WinJavaEnv
 * Date: 24.11.13
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
public interface OrderDetaitlsRepositrory extends JpaRepository<OrderDetails, Long> {
    Page<OrderDetails> findAll(Pageable pageable);
}
