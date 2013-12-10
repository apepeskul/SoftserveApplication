package com.springapp.mvc.model;

import javax.persistence.*;
@Entity
public class Dimension
{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String name;

    @Basic
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

