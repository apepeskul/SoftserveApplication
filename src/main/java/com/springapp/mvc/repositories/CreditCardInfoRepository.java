package com.springapp.mvc.repositories;

import com.springapp.mvc.model.CreditCardInfo;
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
public interface CreditCardInfoRepository extends JpaRepository<CreditCardInfo, Long> {
    Page<CreditCardInfo> findByCreditCardNumberStartingWith( String creditCardNumber, Pageable pageable);
    Page<CreditCardInfo> findAll(Pageable pageable);
}
