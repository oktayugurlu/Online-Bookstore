package com.BoomBook.Model;

import java.util.ArrayList;
import java.util.List;

public class BookComparison {

    private Book firstBook;
    private Book secondBook;
    private boolean firstRequest;
    private List<Category> categories;
    private List<Integer> ratings;
    private boolean showFirstRate;
    private boolean showSecondRate;

    public BookComparison(boolean firstRequest) {
        this.firstRequest = firstRequest;
        categories = new ArrayList<>();
        ratings = new ArrayList<>();
    }

    public Book getFirstBook() {
        return firstBook;
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(List<Integer> ratings) {
        this.ratings = ratings;
    }

    public void setFirstBook(Book firstBook) {
        this.firstBook = firstBook;
    }

    public Book getSecondBook() {
        return secondBook;
    }

    public boolean isShowFirstRate() {
        return showFirstRate;
    }

    public void setShowFirstRate(boolean showFirstRate) {
        this.showFirstRate = showFirstRate;
    }

    public boolean isShowSecondRate() {
        return showSecondRate;
    }

    public void setShowSecondRate(boolean showSecondRate) {
        this.showSecondRate = showSecondRate;
    }

    public void setSecondBook(Book secondBook) {
        this.secondBook = secondBook;
    }

    public boolean isFirstRequest() {
        return firstRequest;
    }

    public void setFirstRequest(boolean firstRequest) {
        this.firstRequest = firstRequest;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "BookComparison{" +
                "firstBook=" + firstBook +
                ", secondBook=" + secondBook +
                ", firstRequest=" + firstRequest +
                '}';
    }
}
