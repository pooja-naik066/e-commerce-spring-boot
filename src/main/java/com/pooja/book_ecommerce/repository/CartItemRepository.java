package com.pooja.book_ecommerce.repository;

import com.pooja.book_ecommerce.entity.CartItem;
import com.pooja.book_ecommerce.entity.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {

    Optional<CartItem> findByCart_CartIdAndBook_BookId(Long cartId, Long bookId);

    Optional<List<CartItem>> findByCart_CartId(Long cartId);

    void deleteByCart_CartId(Long cartId);

}
