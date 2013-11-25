package com.springapp.mvc.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Рамазан
 * Date: 11/4/13
 * Time: 11:57 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity (name = "order_")
public class Order
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private Long merchId;

    @Basic
    private Long orderNumber;

    @Basic
    @Temporal(value=TemporalType.DATE)
    private Date deliveryDate;

    @Basic
    @Temporal(value=TemporalType.DATE)
    private Date preferableDate;

    @Basic
    @Temporal(value=TemporalType.DATE)
    private Date creationDate;

    @Basic
    private Status status;

    @Basic
    private BigDecimal totalPrice;

    @OneToMany (cascade = CascadeType.ALL)
    @JoinTable(name = "items_list")
    private Set<OrderDetails> orderDetailsSet; // список товаров данного заказа

    @ManyToOne(cascade = CascadeType.ALL)
    private User customerId;

    @OneToOne(cascade = CascadeType.ALL)
    private CreditCardInfo payment;

    public enum Status { Created, Pending, Ordered, Delivered } //  возможные состояния заказа

    // геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCustomer() {
        return customerId;
    }

    public void setCustomerId (User customerId) {
        this.customerId = customerId;
    }

    public Long getMerchId() {
        return merchId;
    }

    public void setMerchId(Long merchId) {
        this.merchId = merchId;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getPreferableDate() {
        return preferableDate;
    }

    public void setPreferableDate(Date preferableDate) {
        this.preferableDate = preferableDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CreditCardInfo getPayment() {
        return payment;
    }

    public void setPayment(CreditCardInfo payment) {
        this.payment = payment;
    }

    public Set<OrderDetails> getOrderDetailsSet() {
        return orderDetailsSet;
    }

    public void setOrderDetailsSet(Set<OrderDetails> orderDetailsSet) {
        this.orderDetailsSet = orderDetailsSet;
    }
}

