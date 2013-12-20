package com.springapp.mvc.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity (name = "order_")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User merchId;

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

    /*@OneToMany  (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn( name="order_deteails_id", referencedColumnName = "id")*/
    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn (name = "details_id")

    private Collection<OrderDetails> orderDetailsArray;

    public void addOrderDetail(OrderDetails orderDetails) {
        if (orderDetailsArray==null) {
            orderDetailsArray = new ArrayList <OrderDetails>();
        }
        if (!orderDetailsArray.contains(orderDetails)) {
            orderDetailsArray.add(orderDetails);
        }
    }

    public Collection<OrderDetails> getOrderDetails() {
        return orderDetailsArray;
    }

    public void  setOrderDetailsArray (Collection <OrderDetails> orderDetailsArray){
        this.orderDetailsArray=orderDetailsArray;
    }

    public void deleteOrderDetail (OrderDetails orderDetails){
        this.getOrderDetails().remove(orderDetails);
    }
    // private Set<OrderDetails> orderDetailsSet = new HashSet<OrderDetails>();

    @ManyToOne
    private User customerId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CreditCardInfo payment;

    public enum Status {
        Created, Pending, Ordered, Delivered
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCustomerId() {
        return customerId;
    }

    public void setCustomerId (User customerId) {
        this.customerId = customerId;
    }

    public User getMerchId() {
        return merchId;
    }

    public void setMerchId(User merchId) {
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

    /*public Set <OrderDetails> getOrderDetailsSet() {
        return orderDetailsSet;
    }

    public void setOrderDetailsSet(Set<OrderDetails> orderDetailsSet) {
        this.orderDetailsSet = orderDetailsSet;
    }*/

}

