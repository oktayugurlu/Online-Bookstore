package com.BoomBook.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
@Table(name="subcategory")
public class Subcategory {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;


    @Column(name="title")
    private String title;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    // define constructors

    public Subcategory() {

    }

    public Subcategory(int id, String Title, Category category) {
        this.id = id;
        this.title = Title;
        this.category = category;

    }

    public Subcategory(int id, String title) {
        this.id = id;
        this.title = title;

    }

    public Subcategory(String title, Category category) {
        this.title = title;
        this.category = category;
    }

    public Subcategory(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "Subcategory [id=" + id + ", Title=" + title;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
