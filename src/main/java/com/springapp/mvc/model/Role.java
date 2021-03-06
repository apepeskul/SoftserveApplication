package com.springapp.mvc.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Basic
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long role_id) {
        this.id = role_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String role) {
        this.description = role;
    }

    @Override
    public String getAuthority() {
        return description;
    }
}

