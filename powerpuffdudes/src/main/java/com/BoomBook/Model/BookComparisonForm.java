package com.BoomBook.Model;

public class BookComparisonForm {

    private String firstBook;
    private String secondBook;

    public String getFirstBook() {
        return firstBook;
    }

    public void setFirstBook(String firstBook) {
        this.firstBook = firstBook;
    }

    public String getSecondBook() {
        return secondBook;
    }

    public void setSecondBook(String secondBook) {
        this.secondBook = secondBook;
    }

    @Override
    public String toString() {
        return "BookComparisonForm{" +
                "firstBook='" + firstBook + '\'' +
                ", secondBook='" + secondBook + '\'' +
                '}';
    }
}
