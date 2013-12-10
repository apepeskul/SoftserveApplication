package com.springapp.mvc.repositories;

import com.springapp.mvc.model.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Page<UserInfo> findAll(Pageable pageable);
}
