package com.pooja.book_ecommerce.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
public class CartItemId implements Serializable {

    private Long cartId;
    private Long bookId;

    public CartItemId() {
    }

    public CartItemId(Long bookId, Long cartId) {
        this.bookId=bookId;
        this.cartId=cartId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
