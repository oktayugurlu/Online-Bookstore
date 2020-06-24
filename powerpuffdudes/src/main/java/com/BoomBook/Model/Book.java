package com.BoomBook.Model;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name="subcategory_id", columnDefinition="int default 1")
    @Cascade(CascadeType.SAVE_UPDATE)
    private Subcategory subcategory;

    @Column(name="author_name")
    private String authorName;

    @Column(name="publish_year")
    private int year ;

    @Column(name="title")
    private String title;

    @Column(name="isbn")
    private String isbn;

    @Column(name="image_url")
    private String imageURL;

    @Column(name="publisher_name")
    private String publisherName;

    @Column(name="added_date")
    private LocalDateTime addedDate = LocalDateTime.now();

    @Column(name="subjects")
    private String subject;

    @Column(name="count")
    private int count;

    @Column(name="price")
    private float price;

    @Column(name="price_with_campaign")
    private float priceWithCampaign;




    @OneToOne(cascade = javax.persistence.CascadeType.ALL,mappedBy = "book")
    private Campaign campaign;


    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private List<Cart> carts;

    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private List<BookComment> comments;


    //Constructors

    public Book(){

    }

    public Book(int id, Subcategory subcategory, String authorName, int year, String title,
                String isbn, String imageURL, String publisherName, LocalDateTime addedDate, String subject,
                int count, float price){
        this.id = id;
        this.subcategory = subcategory;
        this.authorName = authorName;
        this.year = year;
        this.title = title;
        this.isbn = isbn;
        this.imageURL = imageURL;
        this.publisherName = publisherName;
        this.addedDate = addedDate;
        this.subject = subject;
        this.count = count;
        this.price = price;

        this.toString();
    }

    public List<BookComment> getComments() {
        return comments;
    }

    public void setComments(List<BookComment> comments) {
        this.comments = comments;
    }

    public float getPriceWithCampaign() {
        return priceWithCampaign;
    }

    public void setPriceWithCampaign(float priceWithCampaign) {
        this.priceWithCampaign = priceWithCampaign;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public Book(Subcategory subcategory, String authorName, int year, String title,
                String isbn, String imageURL, String publisherName, LocalDateTime addedDate, String subject,
                int count, float price){
        this.subcategory = subcategory;
        this.authorName = authorName;
        this.year = year;
        this.title = title;
        this.isbn = isbn;
        this.imageURL = imageURL;
        this.publisherName = publisherName;
        this.addedDate = addedDate;
        this.subject = subject;
        this.count = count;
        this.price = price;
        this.toString();
    }

    public Book( Subcategory subcategory, String authorName, int year, String title,
                String isbn, String imageURL, String publisherName, String subject,
                int count, float price){

        this.subcategory = subcategory;
        this.authorName = authorName;
        this.year = year;
        this.title = title;
        this.isbn = isbn;
        this.imageURL = imageURL;
        this.publisherName = publisherName;
        this.subject = subject;
        this.count = count;
        this.price = price;
        System.out.println("merhaba");
        this.toString();
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", subcategory=" + subcategory +
                ", authorName='" + authorName + '\'' +
                ", year=" + year +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", publisherName='" + publisherName + '\'' +
                ", addedDate=" + addedDate +
                ", subject='" + subject + '\'' +
                ", count=" + count +  '\'' +
                ", price='" + price +
                '}';
    }


    //PREREMOVE

    @PreRemove
    public void preRemoveSubcategory() {
        setSubcategory(null);
    }

    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

}
