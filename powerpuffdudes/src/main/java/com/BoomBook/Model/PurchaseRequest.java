package com.BoomBook.Model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="Purchase_Request")
public class PurchaseRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="purchase_request_id")
    private List<Cart> carts;

    @Column(name="is_confirmed")
    private int isConfirmed;

    @Column(name="purchase_date")
    private LocalDateTime purchaseDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "courier_company_id")
    private CourierCompany courierCompany;

    @ManyToOne
    @JoinColumn(name = "payment_service_id")
    private PaymentService paymentServiceID;


    @OneToOne(cascade = javax.persistence.CascadeType.ALL,mappedBy = "purchaseRequest")
    private InCargo inCargo;

    @OneToOne
    @JoinColumn( name="billing_information_id")
    private BillingInformation billingInformation;

    public BillingInformation getBillingInformation() {
        return billingInformation;
    }

    public void setBillingInformation(BillingInformation billingInformation) {
        this.billingInformation = billingInformation;
    }

    public PurchaseRequest(List<Cart> carts, int isConfirmed, LocalDateTime purchaseDate, CourierCompany courierCompany, PaymentService paymentServiceID, InCargo inCargo) {
        this.carts = carts;
        this.isConfirmed = isConfirmed;
        this.purchaseDate = purchaseDate;
        this.courierCompany = courierCompany;
        this.paymentServiceID = paymentServiceID;
        this.inCargo = inCargo;
    }

    public PurchaseRequest() {
    }



    public InCargo getInCargo() {
        return inCargo;
    }

    public void setInCargo(InCargo inCargo) {
        this.inCargo = inCargo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    @Override
    public String toString() {
        return "PurchaseRequest{" +
                "id=" + id +
                ", carts=" + carts +
                ", isConfirmed=" + isConfirmed +
                ", purchaseDate=" + purchaseDate +
                ", courierCompany=" + courierCompany +
                ", paymentServiceID=" + paymentServiceID +
                '}';
    }

    public int getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(int isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public CourierCompany getCourierCompany() {
        return courierCompany;
    }

    public void setCourierCompany(CourierCompany courierCompany) {
        this.courierCompany = courierCompany;
    }

    public PaymentService getPaymentServiceID() {
        return paymentServiceID;
    }

    public void setPaymentServiceID(PaymentService paymentServiceID) {
        this.paymentServiceID = paymentServiceID;
    }
}
