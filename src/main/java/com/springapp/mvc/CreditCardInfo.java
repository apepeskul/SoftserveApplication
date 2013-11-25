package com.springapp.mvc;

/**
 * Created with IntelliJ IDEA.
 * User: Рамазан
 * Date: 11/4/13
 * Time: 11:59 PM
 * To change this template use File | Settings | File Templates.
 */
import javax.persistence.*;
import java.util.Date;
@Entity
public class CreditCardInfo
{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private CreditCardType type;

    @Basic
    @Column (length = 16)
    private String creditCardNumber;

    @Basic
    private Long CVV2Code;

    @Basic
    @Temporal(value=TemporalType.DATE)
    private Date expiryDate;

    @Basic
    @Temporal(value=TemporalType.DATE)
    private Date startDate;       // maestro only

    @Basic
    private Long issueNumber; // maestro only

    // геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public enum CreditCardType {Visa, MasterCard, AmericanExpress, Maestro }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Long getCVV2Code() {
        return CVV2Code;
    }

    public void setCVV2Code(Long CVV2Code) {
        this.CVV2Code = CVV2Code;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Long getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(Long issueNumber) {
        this.issueNumber = issueNumber;
    }

    public CreditCardType getType() {
        return type;
    }

    public void setType(CreditCardType type) {
        this.type = type;
    }






}
