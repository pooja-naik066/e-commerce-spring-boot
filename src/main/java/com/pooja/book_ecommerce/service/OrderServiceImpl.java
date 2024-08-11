package com.pooja.book_ecommerce.service;

import com.pooja.book_ecommerce.entity.*;
import com.pooja.book_ecommerce.exception.BookNotFoundException;
import com.pooja.book_ecommerce.exception.CartException;
import com.pooja.book_ecommerce.exception.CustomerNotFoundException;
import com.pooja.book_ecommerce.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private CartRepository cartRepository;
    private CustomerRepository customerRepository;
    private CartItemRepository cartItemRepository;
    private OrderItemRepository orderItemRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                        CartRepository cartRepository,
                        CustomerRepository customerRepository,
                        CartItemRepository cartItemRepository,
                        OrderItemRepository orderItemRepository){
        this.orderRepository=orderRepository;
        this.cartRepository=cartRepository;
        this.customerRepository=customerRepository;
        this.cartItemRepository=cartItemRepository;
        this.orderItemRepository=orderItemRepository;
    }


    @Transactional
    public OrderDTO createOrderFromCart(Long customerId) {
        Optional<Customer> customer=customerRepository.findById(customerId);
        if(customer.isEmpty()){
            throw  new CustomerNotFoundException("Customer not found");
        }
        Cart cart=cartRepository.findByCustomer_CustomerId(customerId);
        if(cart==null) {
            throw new CartException("Cart Not found");
        }
        List<CartItem> list=cart.getItems();
        if (list == null || list.isEmpty()) {
            throw new BookNotFoundException("Cart is Empty");
        }

        Order order=new Order();
        order.setCustomer(customer.get());
        order.setOrderDate(new java.util.Date());
        order.setTotalAmount(cart.getTotalAmount());
        orderRepository.save(order);

        for (CartItem cartItem : list) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setBook(cartItem.getBook());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItemRepository.save(orderItem);
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomerName(customer.get().getName());
        orderDTO.setTotalAmount(order.getTotalAmount());

        List<BookCartDTO> bookCart = list.stream().map(this::convertToBookCartDTO).collect(Collectors.toList());
        orderDTO.setBookCart(bookCart);

        cart.getItems().clear();
        cartRepository.save(cart);
        cartItemRepository.deleteByCart_CartId(cart.getCartId());
        return orderDTO;
    }


    public List<OrderDTO> getOrderDetails(Long customerId) {
        List<Order> orders = orderRepository.findByCustomer_CustomerId(customerId);
        if(orders.isEmpty()){
            throw new CartException("No order found");
        }
        List<OrderDTO> list = new ArrayList<>();
        OrderDTO orderDTO = new OrderDTO();
        BookCartDTO bookCartDTO = new BookCartDTO();

        for (Order order : orders) {
            List<BookCartDTO> bookList = new ArrayList<>();
            orderDTO = new OrderDTO();
            orderDTO.setCustomerName(order.getCustomer().getName());
            orderDTO.setTotalAmount(order.getTotalAmount());
            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem item : orderItems) {
                bookCartDTO = new BookCartDTO(item.getBook().getTitle(), item.getQuantity());
                bookList.add(bookCartDTO);
            }
            orderDTO.setBookCart(bookList);
            list.add(orderDTO);
        }
        return list;
    }

    private BookCartDTO convertToBookCartDTO(CartItem cartItem)
    {
        BookCartDTO bookCartDTO = new BookCartDTO();
        bookCartDTO.setBookTitle(cartItem.getBook().getTitle());
        bookCartDTO.setQuantity(cartItem.getQuantity());
        return bookCartDTO;
    }
}
