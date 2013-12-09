package com.springapp.mvc.repositories;
 import com.springapp.mvc.model.Role;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.Pageable;
 import org.springframework.data.jpa.repository.JpaRepository;
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findById (Long id);
    Role findByDescription (String description);
    Page<Role> findByDescriptionStartingWith(String description, Pageable pageable);
    Page<Role> findAll(Pageable pageable);
}
