package com.springapp.mvc.repositories;

import com.springapp.mvc.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findByNameStartingWith(String name, Pageable pageable);
    Page<Item> findAll(Pageable pageable);
}
