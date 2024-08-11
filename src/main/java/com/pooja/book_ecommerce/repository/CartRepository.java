package com.pooja.book_ecommerce.repository;

import com.pooja.book_ecommerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByCustomer_CustomerId(Long customerId);
}
