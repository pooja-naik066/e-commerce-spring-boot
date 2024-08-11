package com.pooja.book_ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;


@Entity
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


    public CartItemId getId() {
        return id;
    }

    public void setId(CartItemId id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public @Min(value = 0) Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(@Min(value = 0) Integer quantity) {
        this.quantity = quantity;
    }
}

