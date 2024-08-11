package com.pooja.book_ecommerce.entity;

import java.util.List;

public class OrderDTO {
    private String customerName;
    private Double totalAmount;
    private List<BookCartDTO> bookCart;

    public OrderDTO(){

    }

    public OrderDTO(String customerName, Double totalAmount, List<BookCartDTO> bookCart) {
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.bookCart = bookCart;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<BookCartDTO> getBookCart() {
        return bookCart;
    }

    public void setBookCart(List<BookCartDTO> bookCart) {
        this.bookCart = bookCart;
    }
}
