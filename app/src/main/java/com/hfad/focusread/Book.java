package com.hfad.focusread;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties

public class Book {
    private  String bookTitle;
    private  String bookAuthor;
    private  int numberOfPages;
    private int startPage;
    private String status;



    public Book(String bookTitle, String authorName, int numberOfPages) {
        this.bookTitle = bookTitle;
        this.bookAuthor = authorName;
        this.numberOfPages = numberOfPages;
        this.startPage = 1;
        this.status = "Not Started";
    }

    public Book() {

    }

    public String getBookTitle()
    {
        return bookTitle;
    }

    public String getBookAuthor()
    {
        return bookAuthor;
    }

    public int getNumberOfPages()
    {
        return numberOfPages;
    }

    public int getStartPage() {
        return startPage;
    }

    public String getStatus() {
        return status;
    }
}