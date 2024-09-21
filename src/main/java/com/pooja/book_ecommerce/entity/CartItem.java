package com.pooja.book_ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;


@Entity
@Data
public class CartItem {

    @EmbeddedId
    private CartItemId id = new CartItemId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cartId")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookId")
    private Book book;


    @Min(value = 0)
    private Integer quantity;

    public CartItem(){

    }


    public CartItem(Cart cart, Book book, Integer quantity) {
        this.cart=cart;
        this.book=book;
        this.quantity=quantity;
    }
}

