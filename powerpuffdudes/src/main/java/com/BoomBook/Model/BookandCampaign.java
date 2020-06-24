package com.BoomBook.Model;

public class BookandCampaign {
    private float percentage;
    public BookandCampaign(){
        this.bookId=0;
    }

    public BookandCampaign(int bookId) {
        this.bookId = bookId;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    private int bookId;
}
