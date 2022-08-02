package com.hfad.focusread;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties

/** class Book containing the attributes for the list**/
public class Book {
    private  String bookTitle;
    private  String bookAuthor;
    private  int numberOfPages;
    private int startPage;
    private String status;
    private String bookId;


    public void setBookTitle(String bookTitle) {

        this.bookTitle = bookTitle;
    }

    public void setBookAuthor(String bookAuthor) {

        this.bookAuthor = bookAuthor;
    }

    public void setNumberOfPages(int numberOfPages) {

        this.numberOfPages = numberOfPages;
    }

    public void setStartPage(int startPage) {

        this.startPage = startPage;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public void setBookId(String bookId) {

        this.bookId = bookId;
    }

    public Book(String bookTitle, String authorName, int numberOfPages, String bookId) {
        this.bookTitle = bookTitle;
        this.bookAuthor = authorName;
        this.numberOfPages = numberOfPages;
        this.startPage = 1;
        this.status = "Not Started";
        this.bookId = bookId;
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

    public String getBookId() {
        return bookId;
    }
}