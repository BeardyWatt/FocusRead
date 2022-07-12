package com.hfad.focusread;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties

public class Book {
    private  String bookTitle;
    private  String authorName;
    private  String numberOfPages;

    public Book() {
    }

    public Book(String bookTitle, String authorName, String numberOfPages) {
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.numberOfPages = numberOfPages;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getNumberOfPages() {
        return numberOfPages;
    }
}