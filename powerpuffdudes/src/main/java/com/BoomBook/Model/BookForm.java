package com.BoomBook.Model;

public class BookForm {
    private int id;

    private int subcategory;

    @Override
    public String toString() {
        return "BookForm{" +
                "id=" + id +
                ", subcategory=" + subcategory +
                ", category=" + category +
                ", subcategoryForUpdate='" + subcategoryForUpdate + '\'' +
                ", categoryForUpdate='" + categoryForUpdate + '\'' +
                ", authorName='" + authorName + '\'' +
                ", year=" + year +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", publisherName='" + publisherName + '\'' +
                ", subject='" + subject + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }

    // New added after aybar
    private int category;

    private String subcategoryForUpdate;

    // New added after aybar
    private String categoryForUpdate;

    private String authorName;

    private int year ;

    private String title;

    private String isbn;

    private String imageURL;

    private String publisherName;

    private String subject;

    private int count;

    private float price;


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
    public int getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(int subcategory) {
        this.subcategory = subcategory;
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }


    public String getSubcategoryForUpdate() {
        return subcategoryForUpdate;
    }

    public void setSubcategoryForUpdate(String subcategoryForUpdate) {
        this.subcategoryForUpdate = subcategoryForUpdate;
    }

    public String getCategoryForUpdate() {
        return categoryForUpdate;
    }

    public void setCategoryForUpdate(String categoryForUpdate) {
        this.categoryForUpdate = categoryForUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
