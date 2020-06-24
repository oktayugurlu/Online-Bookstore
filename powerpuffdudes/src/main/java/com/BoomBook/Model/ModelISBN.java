package com.BoomBook.Model;

public class ModelISBN {

    private boolean current_isbn;
    private boolean isComeFromAddBook;

    public boolean getIsComeFromAddBook() {
        return isComeFromAddBook;
    }

    public void setComeFromAddBook(boolean comeFromAddBook) {
        isComeFromAddBook = comeFromAddBook;
    }

    public boolean getIsCurrent_isbn() {
        return current_isbn;
    }

    public void setCurrent_isbn(boolean current_isbn) {
        this.current_isbn = current_isbn;
    }
}
