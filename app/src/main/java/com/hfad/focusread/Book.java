package com.hfad.focusread;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties

public class Book {
    private  String bookTitle;
    private  String bookAuthor;
    private  String numberOfPages;

    public Book(String bookTitle, String authorName, String numberOfPages) {
        this.bookTitle = bookTitle;
        this.bookAuthor = authorName;
        this.numberOfPages = numberOfPages;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getNumberOfPages() {
        return numberOfPages;
    }
}