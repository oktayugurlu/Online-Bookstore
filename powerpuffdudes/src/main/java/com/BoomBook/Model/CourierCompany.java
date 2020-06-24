package com.BoomBook.Model;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Courier_Company")
public class CourierCompany {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column( name="url")
    private String url;

    @Column(name = "phone")
    private String phone;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "price")
    private float price;


    // CONSTRUCTORS

    public CourierCompany() {
    }

    public CourierCompany(String url,String phone,String companyName,float price) {
        this.url = url;
        this.phone = phone;
        this.companyName = companyName;
        this.price = price;
    }


    // GETTERS AND SETTERS

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getCompanyName() { return companyName; }

    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public float getPrice() { return price; }

    public void setPrice(float price) { this.price = price; }


    @Override
    public String toString() {
        return "CourierCompany{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", phone='" + phone + '\'' +
                ", companyName='" + companyName + '\'' +
                ", price=" + price +
                '}';
    }
}
