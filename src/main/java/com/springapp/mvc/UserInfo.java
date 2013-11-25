package com.springapp.mvc;

/**
 * Created with IntelliJ IDEA.
 * User: Рамазан
 * Date: 11/11/13
 * Time: 6:14 PM
 * To change this template use File | Settings | File Templates.
 */

import javax.persistence.*;

@Entity
public class UserInfo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private CustomerType customerType; // статус пользователя

    @Basic
    private Long balance; //  общее количество потраченных денег

    @OneToOne (cascade = CascadeType.ALL)
    private User userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public enum CustomerType {Standard, Silver, Gold, Platinum};

    // геттеры и сеттеры
    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
