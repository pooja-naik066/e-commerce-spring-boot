package com.pooja.book_ecommerce.service;

import com.pooja.book_ecommerce.entity.Book;
import com.pooja.book_ecommerce.exception.BookNotFoundException;
import com.pooja.book_ecommerce.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {


    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        Optional<Book> book= bookRepository.findById(id);
        if(book.isPresent()){
            return book.get();
        }
        else
            throw new BookNotFoundException("Book with id "+id+" not found");
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book update(Long id, Book book) {
        Optional<Book> tempBook= bookRepository.findById(id);
        if(tempBook.isPresent()){
            Book existingBook=tempBook.get();
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setGenre(book.getGenre());
            existingBook.setDescription(book.getDescription());
            existingBook.setPrice(book.getPrice());
            existingBook.setQuantity(book.getQuantity());
            Book savedBook =bookRepository.save(existingBook);
            return savedBook;
        }
        else
            throw new BookNotFoundException("Book with id "+id+" not found");
    }

    public String deleteBook(Long id) {
        Optional<Book> tempBook= bookRepository.findById(id);
        if(tempBook.isPresent()){
            bookRepository.deleteById(id);
            return "Deleted book with id "+id+" ";
        }
        else
            throw new BookNotFoundException("Book with id "+id+" not found");
    }


}

