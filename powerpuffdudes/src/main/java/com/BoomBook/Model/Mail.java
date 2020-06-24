package com.BoomBook.Model;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

@Entity
@Table(name="Mail")
public class Mail  {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;


    @ManyToOne
    @JoinColumn( name="customer_id")
    private Customer customer;


    @ManyToOne
    @JoinColumn( name="page_admin_id")
    private PageAdmin pageAdmin;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "send_date")
    private LocalDateTime sendDate;



    // CONSTRUCTORS

    public Mail() {
    }

    public Mail(Customer customer, PageAdmin pageAdmin, String title, String content, LocalDateTime sendDate) {
        this.customer = customer;
        this.pageAdmin = pageAdmin;
        this.title = title;
        this.content = content;
        this.sendDate = sendDate;
    }

    // GETTERS AND SETTERS

    public Customer getCustomer() { return customer; }

    public void setCustomer(Customer customer) { this.customer = customer; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public PageAdmin getPageAdmin() { return pageAdmin; }

    public void setPageAdmin(PageAdmin pageAdmin) { this.pageAdmin = pageAdmin; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public LocalDateTime getSendDate() { return sendDate; }

    public void setSendDate(LocalDateTime sendDate) { this.sendDate = sendDate; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }


    @Override
    public String toString() {
        return "Mail{" +
                ", customer=" + customer +
                ", pageAdmin=" + pageAdmin +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", sendDate=" + sendDate +
                '}';
    }
}
