package com.springapp.mvc.repositories;
 import com.springapp.mvc.model.Role;
 import org.springframework.data.jpa.repository.JpaRepository;
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findById (Long id);
    Role findByDescription (String description);
}
