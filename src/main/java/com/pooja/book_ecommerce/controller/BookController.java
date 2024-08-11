package com.pooja.book_ecommerce.controller;

import com.pooja.book_ecommerce.entity.Book;
import com.pooja.book_ecommerce.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

     private BookService bookService;

    @Autowired
    public BookController(BookService bookService){
         this.bookService=bookService;
     }

     @GetMapping
     public ResponseEntity<List<Book>> getAllBooks() {
         List<Book> books= bookService.getAllBooks();
         return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
     }

     @GetMapping("/{id}")
     public ResponseEntity<Book> getBookById(@PathVariable Long id) {
         Book book= bookService.getBookById(id);
         return new ResponseEntity<Book>(book, HttpStatus.FOUND);
     }

        @PostMapping
        public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
            Book savedBook= bookService.saveBook(book);
            return new ResponseEntity<Book>(savedBook, HttpStatus.ACCEPTED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Book> updateBook(@Valid @PathVariable Long id, @RequestBody Book book) {
            Book updatedBook=bookService.update(id,book);
            return new ResponseEntity<Book>(updatedBook,HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteBook(@PathVariable Long id) {
            String message=bookService.deleteBook(id);
            return new ResponseEntity(message,HttpStatus.OK);
        }

}

