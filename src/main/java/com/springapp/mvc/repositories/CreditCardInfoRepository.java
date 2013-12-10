package com.springapp.mvc.repositories;

import com.springapp.mvc.model.CreditCardInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardInfoRepository extends JpaRepository<CreditCardInfo, Long> {
    Page<CreditCardInfo> findByCreditCardNumberStartingWith( String creditCardNumber, Pageable pageable);
    Page<CreditCardInfo> findAll(Pageable pageable);
}
