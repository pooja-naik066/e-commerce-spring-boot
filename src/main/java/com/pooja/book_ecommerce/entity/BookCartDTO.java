package com.pooja.book_ecommerce.entity;

public class BookCartDTO {
    private String bookTitle;
    private Integer quantity;

    public BookCartDTO() {

    }

    public BookCartDTO(String title, Integer quantity) {
        this.bookTitle=title;
        this.quantity=quantity;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
