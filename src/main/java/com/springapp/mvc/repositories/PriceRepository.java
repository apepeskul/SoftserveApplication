package com.springapp.mvc.repositories;

import com.springapp.mvc.model.Item;
import com.springapp.mvc.model.Price;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {
    List<Price> findByItemId(Item itemId);
    Page<Price> findAll(Pageable pageable);
}
