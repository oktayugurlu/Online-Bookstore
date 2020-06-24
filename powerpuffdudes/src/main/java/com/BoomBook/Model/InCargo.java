package com.BoomBook.Model;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="In_Cargo")
public class InCargo {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @OneToOne
    @JoinColumn( name="purchase_request_id")
    private PurchaseRequest purchaseRequest;

    @Column(name="export_date")
    private LocalDateTime exportDate = LocalDateTime.now();

    @Column(name="arrival_date")
    private LocalDateTime arrivalDate = LocalDateTime.now().plusDays(3);


    // CONSTRUCTOR

    public InCargo(PurchaseRequest purchaseRequest, LocalDateTime exportDate, LocalDateTime arrivalDate) {
        this.purchaseRequest = purchaseRequest;
        this.exportDate = exportDate;
        this.arrivalDate = arrivalDate;
    }

    public InCargo() {

    }

    // GETTERS AND SETTERS


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PurchaseRequest getPurchaseRequest() {
        return purchaseRequest;
    }

    public void setPurchaseRequest(PurchaseRequest purchaseRequest) {
        this.purchaseRequest = purchaseRequest;
    }

    public LocalDateTime getExportDate() {
        return exportDate;
    }

    public void setExportDate(LocalDateTime exportDate) {
        this.exportDate = exportDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }


    @Override
    public String toString() {
        return "InCargo{" +
                "id=" + id +
                ", purchaseRequest=" + purchaseRequest +
                ", exportDate=" + exportDate +
                ", arrivalDate=" + arrivalDate +
                '}';
    }
}
