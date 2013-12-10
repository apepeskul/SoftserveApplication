package com.springapp.mvc.model;

import javax.persistence.*;
@Entity
 public class OrderDetails{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private Integer quantity;

    @ManyToOne
    private Price price;

   /* @ManyToOne
    @JoinColumn (name = "order_id")
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
*/

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Price getPrice() {
        return price;
    }
    public void setPrice(Price price) {
        this.price = price;
    }

   /* public Dimension getDimension() {
        return dimension;
    }
    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   /* Item getItemId() {
        return itemId;
    }

    void setItemId(Item itemId) {
        this.itemId = itemId;
    }*/
}

