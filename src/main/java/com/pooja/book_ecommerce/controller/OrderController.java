package com.pooja.book_ecommerce.controller;

import com.pooja.book_ecommerce.entity.OrderDTO;
import com.pooja.book_ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders/{customerId}")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrderFromCart(@PathVariable Long customerId){
        OrderDTO orderDTO =orderService.createOrderFromCart(customerId);
        return new ResponseEntity<OrderDTO>(orderDTO,HttpStatus.ACCEPTED);
    }
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getOrderDetails(@PathVariable Long customerId){
        List<OrderDTO> orderDTOList=  orderService.getOrderDetails(customerId);
        return  new ResponseEntity<List<OrderDTO>>(orderDTOList,HttpStatus.FOUND);
    }


}