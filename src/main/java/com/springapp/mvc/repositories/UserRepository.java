package com.springapp.mvc.repositories;

import com.springapp.mvc.model.Role;
import com.springapp.mvc.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor <User> {

    @Query ("select u from account u where u.login = ?1")
    User findByLogin (String login);

    User findByEmail (String email);

    List <User> findByRole (Role role);

    Page<User> findByFirstNameContaining(String firstName, Pageable pageable);

    Page<User> findByLoginContaining(String login, Pageable pageable);

    Page<User> findByEmailContaining(String email, Pageable pageable);

    Page<User> findByRoleContaining(Role role, Pageable pageable);

    Page<User> findByRegionContaining(Enum region, Pageable pageable);

    Page<User> findByLoginContainingAndEmailContainingAndFirstNameContaining(
            String login, String email, String firstName, Pageable pageable
    );

    Page<User> findAll(Pageable pageable);

}