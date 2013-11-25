package com.springapp.mvc;

/**
 * Created with IntelliJ IDEA.
 * User: Рамазан
 * Date: 11/4/13
 * Time: 11:58 PM
 * To change this template use File | Settings | File Templates.
 */
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

    // геттеры и сеттеры
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

