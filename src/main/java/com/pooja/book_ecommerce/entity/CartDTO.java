package com.pooja.book_ecommerce.entity;

import lombok.Data;

@Data
public class CartDTO {
    private Long bookId;
    private Integer quantity;
}
