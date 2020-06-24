package com.BoomBook.Model;

import javax.persistence.*;

@Entity
@Table(name="Payment_Service")
public class PaymentService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="payment_name")
    private String paymentName;

    @Column(name="account_number")
    private String accountNumber;

    public PaymentService() {
    }

    public PaymentService(int id, String paymentName, String accountNumber) {
        this.id = id;
        this.paymentName = paymentName;
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "PaymentService{" +
                "id=" + id +
                ", payment_name='" + paymentName + '\'' +
                ", account_number=" + accountNumber +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
