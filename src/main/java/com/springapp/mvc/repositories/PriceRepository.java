package com.springapp.mvc.repositories;

import com.springapp.mvc.model.Item;
import com.springapp.mvc.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: WinJavaEnv
 * Date: 25.11.13
 * Time: 6:24
 * To change this template use File | Settings | File Templates.
 */
public interface PriceRepository extends JpaRepository<Price, Long> {
    List<Price> findByItemId(Item itemId);

}
