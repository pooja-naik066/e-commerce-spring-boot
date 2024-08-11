package com.pooja.book_ecommerce.service;

import com.pooja.book_ecommerce.entity.Book;

import java.util.List;

public interface BookService {


    List<Book> getAllBooks();

    Book getBookById(Long id);

    Book saveBook(Book book);

    Book update(Long id, Book book);

    String deleteBook(Long id);
}

