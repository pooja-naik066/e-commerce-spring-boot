package com.pooja.book_ecommerce.repository;

import com.pooja.book_ecommerce.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}