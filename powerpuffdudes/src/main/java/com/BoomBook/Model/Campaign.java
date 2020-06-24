package com.BoomBook.Model;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="campaign")
public class Campaign {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @OneToOne
    @JoinColumn( name="book_id")
    private Book book;

    @Column(name = "discount_percentage")
    private Float discountPercentage;

    @Column(name = "note")
    private String note;

    @Column(name = "image_url")
    private String imageURL;

    // CONSTRUCTORS

    public Campaign() {
    }


    public Campaign(Book book, Float discountPercentage, String note, String imageURL) {
        this.book = book;
        this.discountPercentage = discountPercentage;
        this.note = note;
        this.imageURL = imageURL;
    }

    // GETTERS AND SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "id=" + id +
                ", book=" + book +
                ", discountPercentage=" + discountPercentage +
                ", note='" + note + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
