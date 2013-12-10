package com.springapp.mvc.repositories;

import com.springapp.mvc.model.Dimension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DimensionRepository extends JpaRepository<Dimension, Long> {
    Page<Dimension> findByNameStartingWith(String name, Pageable pageable);
    Page<Dimension> findAll(Pageable pageable);
}
