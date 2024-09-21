package com.pooja.book_ecommerce.entity;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private String customerName;
    private Double totalAmount;
    private List<BookCartDTO> bookCart;


}
