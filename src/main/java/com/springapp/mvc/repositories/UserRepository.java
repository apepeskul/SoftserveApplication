
package com.springapp.mvc.repositories;

import com.springapp.mvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor <User> {
    @Query ("select u from account u where u.login = ?1")
    User findByLogin (String login);
    User findById (Long id);
}