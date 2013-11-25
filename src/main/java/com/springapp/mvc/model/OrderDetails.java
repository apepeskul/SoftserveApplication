package com.springapp.mvc.model;

/**
 * Created with IntelliJ IDEA.
 * User: Рамазан
 * Date: 11/4/13
 * Time: 11:56 PM
 * To change this template use File | Settings | File Templates.
 */
import javax.persistence.*;
@Entity
 public class OrderDetails
{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private Integer quantity;

    @OneToOne (cascade = CascadeType.ALL)
    private Item itemId;

    @OneToOne (cascade = CascadeType.ALL)
    private Price price;

    @OneToOne (cascade = CascadeType.ALL)
    private Dimension dimension;

    // геттеры и сеттеры
    public Price getPrice() {
        return price;
    }
    public void setPrice(Price price) {
        this.price = price;
    }

    public Dimension getDimension() {
        return dimension;
    }
    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    Item getItemId() {
        return itemId;
    }

    void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    Integer getQuantity() {
        return quantity;
    }

    void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

