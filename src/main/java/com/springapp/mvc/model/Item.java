package com.springapp.mvc.model;

/**
 * Created with IntelliJ IDEA.
 * User: Рамазан
 * Date: 11/4/13
 * Time: 11:51 PM
 * To change this template use File | Settings | File Templates.
 */
import javax.persistence.*;import javax.persistence.Basic;import javax.persistence.Entity;import javax.persistence.GeneratedValue;import javax.persistence.GenerationType;import javax.persistence.Id;import java.lang.Integer;import java.lang.Long;import java.lang.String;
@Entity
public class Item
{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String name;

    @Basic
    private String description;

    @Basic
    private Integer quantity;

    // геттеры и сеттеры
    public void setId (Long id)
    {
        this.id = id;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Long getId ()
    {
        return id;
    }

    public String getName ()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}





