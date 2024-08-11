package com.pooja.book_ecommerce.controller;

import com.pooja.book_ecommerce.entity.BookCartDTO;
import com.pooja.book_ecommerce.entity.Cart;
import com.pooja.book_ecommerce.entity.CartItem;
import com.pooja.book_ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts/{customerId}")
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add/{bookId}/quantity/{quantity}")
    public ResponseEntity<BookCartDTO> addBookToCart(@PathVariable Long customerId,
                                              @PathVariable Long bookId,
                                              @PathVariable Integer quantity){
        BookCartDTO bookCartDTO=cartService.addToCart(customerId,bookId,quantity);
        return new ResponseEntity<BookCartDTO>(bookCartDTO, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{bookId}")
    public ResponseEntity<String> removeBookFromCart(@PathVariable Long customerId, @PathVariable Long bookId){
        String message =cartService.removeFromCart(customerId,bookId);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @GetMapping
    public List<BookCartDTO> getCartByCustomerId(@PathVariable Long customerId){
        return cartService.getCartByCustomerId(customerId);
    }

    @PutMapping("/update/{bookId}/quantity/{quantity}")
    public ResponseEntity<BookCartDTO> updateQuantity(@PathVariable Long customerId,
                                                      @PathVariable Long bookId,
                                                      @PathVariable Integer quantity){

        BookCartDTO bookCartDTO=cartService.updateQuantity(customerId,bookId,quantity);
        return new ResponseEntity<BookCartDTO>(bookCartDTO, HttpStatus.OK);

    }


}
