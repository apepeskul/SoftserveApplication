package com.springapp.mvc.repositories;

import com.springapp.mvc.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: WinJavaEnv
 * Date: 25.11.13
 * Time: 6:23
 * To change this template use File | Settings | File Templates.
 */
public interface ItemRepositrory extends JpaRepository<Item, Long> {
    Page<Item> findByNameStartingWith(String name, Pageable pageable);
    Page<Item> findAll(Pageable pageable);
}
