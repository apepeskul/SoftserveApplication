package com.springapp.mvc.repositories;


import com.springapp.mvc.model.Dimension;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: WinJavaEnv
 * Date: 25.11.13
 * Time: 6:24
 * To change this template use File | Settings | File Templates.
 */
public interface DimensionRepository extends JpaRepository<Dimension, Long> {
    Dimension findById(Long id);
}