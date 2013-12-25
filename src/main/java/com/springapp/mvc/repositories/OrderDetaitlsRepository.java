package com.springapp.mvc.repositories;

import com.springapp.mvc.model.OrderDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetaitlsRepository extends JpaRepository<OrderDetails, Long> {

    Page<OrderDetails> findAll(Pageable pageable);

}
