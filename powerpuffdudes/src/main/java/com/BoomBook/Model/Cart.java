package com.BoomBook.Model;

import javax.persistence.*;

@Entity
@Table(name="Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn( name="book_id")
    private Book bookId;

    @ManyToOne
    @JoinColumn( name="customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn( name="purchase_request_id")
    private PurchaseRequest purchaseRequest;


    @Column(name = "count")
    private int count;


    public PurchaseRequest getPurchaseRequest() {
        return purchaseRequest;
    }

    public void setPurchaseRequest(PurchaseRequest purchaseRequest) {
        this.purchaseRequest = purchaseRequest;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", customer=" + customer +
                ", count=" + count +
                '}';
    }

    public Cart() {

    }

    public Cart(Book bookId, Customer customer, int count) {
        this.bookId = bookId;
        this.customer = customer;
        this.count = count;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
