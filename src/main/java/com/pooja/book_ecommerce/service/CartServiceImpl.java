package com.pooja.book_ecommerce.service;

import com.pooja.book_ecommerce.entity.*;
import com.pooja.book_ecommerce.exception.BookNotFoundException;
import com.pooja.book_ecommerce.exception.CartException;
import com.pooja.book_ecommerce.exception.CustomerNotFoundException;
import com.pooja.book_ecommerce.repository.BookRepository;
import com.pooja.book_ecommerce.repository.CartItemRepository;
import com.pooja.book_ecommerce.repository.CartRepository;
import com.pooja.book_ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService{

    private CartRepository cartRepository;
    private CustomerRepository customerRepository;
    private BookRepository bookRepository;
    private CartItemRepository cartItemRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository,
                       CustomerRepository customerRepository,
                       BookRepository bookRepository,
                       CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository=customerRepository;
        this.bookRepository=bookRepository;
        this.cartItemRepository=cartItemRepository;
    }

    public BookCartDTO addToCart(Long customerId, Long bookId, Integer quantity) {
        Optional<Customer> customer=customerRepository.findById(customerId);
        if(customer.isEmpty()){
            throw  new CustomerNotFoundException("Customer not found");
        }
        Optional<Book> book=bookRepository.findById(bookId);
        if(book.isEmpty()){
            throw new BookNotFoundException("Book not found");
        }
        if(book.get().getQuantity()<quantity){
            throw new BookNotFoundException("Book ran out of stock");
        }
        Boolean flag=true;
        BookCartDTO bookCartDTO=new BookCartDTO();
        Cart cart=cartRepository.findByCustomer_CustomerId(customerId);
        List<CartItem> cartItems=cart.getItems();
        if(cartItems.size()>0){
            for(CartItem item:cartItems){
                if(item.getBook().getBookId()==bookId){
                    item.setQuantity(item.getQuantity()+quantity);
                    cartItemRepository.save(item);
                    flag=false;
                    bookCartDTO=new BookCartDTO(book.get().getTitle(),item.getQuantity());
                    break;
                }
            }}
        if(flag) {
            CartItem cartItem = new CartItem(cart, book.get(), quantity);
            cart.addItem(cartItem);
            cartRepository.save(cart);
            bookCartDTO=new BookCartDTO(book.get().getTitle(),quantity);

        }
        book.get().setQuantity(book.get().getQuantity()-quantity);
        bookRepository.save(book.get());
        return bookCartDTO;
    }


    public String removeFromCart(Long customerId, Long bookId) {
        Optional<Customer> customer=customerRepository.findById(customerId);
        if(customer.isEmpty()){
            throw  new CustomerNotFoundException("Customer not found");
        }
        Optional<Book> book=bookRepository.findById(bookId);
        if(book.isEmpty()){
            throw new BookNotFoundException("Book not found");
        }

        Cart cart=cartRepository.findByCustomer_CustomerId(customerId);
        Optional<CartItem> cartItem=cartItemRepository.findByCart_CartIdAndBook_BookId(cart.getCartId(), bookId);
        book.get().setQuantity(book.get().getQuantity()+ cartItem.get().getQuantity());
        bookRepository.save(book.get());
        cartItemRepository.delete(cartItem.get());
        return "Book removed from Cart";
    }


    public List<BookCartDTO> getCartByCustomerId(Long customerId) {
        Optional<Customer> customer=customerRepository.findById(customerId);
        if(customer.isEmpty()){
            throw  new CustomerNotFoundException("Customer not found");
        }
        Cart cart= cartRepository.findByCustomer_CustomerId(customerId);

        List<CartItem> cartItems= cart.getItems();
        if(cartItems.size()==0){
            throw new CartException("Cart is empty!!");
        }
        return cartItems.stream()
                .map(cartItem -> new BookCartDTO(cartItem.getBook().getTitle(), cartItem.getQuantity()))
                .collect(Collectors.toList());

    }


    public BookCartDTO updateQuantity(Long customerId, Long bookId, Integer quantity) {
        Optional<Customer> customer=customerRepository.findById(customerId);
        if(customer.isEmpty()){
            throw  new CustomerNotFoundException("Customer not found");
        }
        Optional<Book> book=bookRepository.findById(bookId);
        if(book.isEmpty()){
            throw new BookNotFoundException("Book not found");
        }

        Cart cart=cartRepository.findByCustomer_CustomerId(customerId);
        List<CartItem> cartItems= cart.getItems();
        Boolean flag=true;
        BookCartDTO bookCartDTO=new BookCartDTO();
        for(CartItem item:cartItems){
            if(item.getBook().getBookId()==bookId){
                book.get().setQuantity(book.get().getQuantity()+ item.getQuantity());
                if(book.get().getQuantity()<quantity){
                    throw new BookNotFoundException("Book ran out of stock");
                }
                item.setQuantity(quantity);
                cartItemRepository.save(item);
                flag=false;
                bookCartDTO=new BookCartDTO(book.get().getTitle(),item.getQuantity());
                break;
            }
        }
        if(flag) {
            throw new BookNotFoundException("Book not found in the cart");
        }

        book.get().setQuantity(book.get().getQuantity()-quantity);
        bookRepository.save(book.get());
        return bookCartDTO;

    }
}
