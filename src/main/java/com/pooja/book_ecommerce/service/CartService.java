package com.pooja.book_ecommerce.service;

import com.pooja.book_ecommerce.entity.BookCartDTO;
import com.pooja.book_ecommerce.entity.CartDTO;

import java.util.List;

public interface CartService {


    BookCartDTO addToCart(Long customerId, CartDTO cartDTO);

    String removeFromCart(Long customerId, Long bookId);

    List<BookCartDTO> getCartByCustomerId(Long customerId);

    BookCartDTO updateQuantity(Long customerId, CartDTO cartDTO);
}
