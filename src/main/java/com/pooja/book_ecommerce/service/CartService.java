package com.pooja.book_ecommerce.service;

import com.pooja.book_ecommerce.entity.BookCartDTO;

import java.util.List;

public interface CartService {


    BookCartDTO addToCart(Long customerId, Long bookId, Integer quantity);

    String removeFromCart(Long customerId, Long bookId);

    List<BookCartDTO> getCartByCustomerId(Long customerId);

    BookCartDTO updateQuantity(Long customerId, Long bookId, Integer quantity);
}
