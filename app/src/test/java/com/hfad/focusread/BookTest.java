package com.hfad.focusread;

import static org.junit.Assert.*;

import org.junit.Test;

public class BookTest {
    Book book = new Book();

    @Test
    public void getBookTitle() {
        book.setBookTitle("test book");
        assert book.getBookTitle().equals("test book");
    }

    @Test
    public void getBookAuthor() {
        book.setBookAuthor("test Author");
        assert book.getBookAuthor().equals("test Author");
    }

    @Test
    public void getNumberOfPages() {
        book.setNumberOfPages(100);
        assert book.getNumberOfPages()== 100;
    }

    @Test
    public void getStartPage() {
        book.setStartPage(100);
        assert book.getStartPage()== 100;
    }

    @Test
    public void getStatus() {
        book.setStatus("In Process");
        assert book.getStatus().equals("In Process");

    }

    @Test
    public void getBookId() {
        book.setBookId("BBBBBB312");
        assert book.getBookId().equals("BBBBBB312");
    }

    @Test
    public void setBookTitle() {
        book.setBookTitle("test book");
        assert book.getBookTitle().equals("test book");
    }

    @Test
    public void setBookAuthor() {
        book.setBookAuthor("test Author");
        assert book.getBookAuthor().equals("test Author");

    }

    @Test
    public void setNumberOfPages() {
        book.setNumberOfPages(100);
        assert book.getNumberOfPages()== 100;
    }

    @Test
    public void setStartPage() {
        book.setStartPage(100);
        assert book.getStartPage()== 100;
    }

    @Test
    public void setStatus() {
        book.setStatus("In Process");
        assert book.getStatus().equals("In Process");
    }

    @Test
    public void setBookId() {
        book.setBookId("BBBBBB312");
        assert book.getBookId().equals("BBBBBB312");
    }
}