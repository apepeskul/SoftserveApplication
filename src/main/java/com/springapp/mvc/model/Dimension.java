package com.springapp.mvc.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Dimension {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @NotNull
    private String name;

    @Basic
    @NotNull
    private Integer multiplex;

    public Long getDimensionId() {
        return id;
    }

    public void setDimensionId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getMultiplex() {
        return multiplex;
    }

    public void setMultiplex(Integer multiplex) {
        this.multiplex = multiplex;
    }

}

