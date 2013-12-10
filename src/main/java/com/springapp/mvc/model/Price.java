package com.springapp.mvc.model;

import javax.persistence.*;

@Entity
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private Long price;

    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item itemId;

    @OneToOne
    @JoinColumn(name = "dimension_id", referencedColumnName = "id")
    private Dimension dimensionId;

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
