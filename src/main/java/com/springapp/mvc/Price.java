package com.springapp.mvc;

/**
 * Created with IntelliJ IDEA.
 * User: Рамазан
 * Date: 11/4/13
 * Time: 11:54 PM
 * To change this template use File | Settings | File Templates.
 */
import javax.persistence.*;

@Entity
public class Price

{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private Long price;

    @OneToOne(cascade = CascadeType.ALL)
    private Item itemId;

    @OneToOne (cascade = CascadeType.ALL)
    private Dimension dimensionId;

    // геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    public Dimension getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(Dimension dimensionId) {
        this.dimensionId = dimensionId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
