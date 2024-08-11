package com.pooja.book_ecommerce.service;


import com.pooja.book_ecommerce.entity.OrderDTO;

import java.util.List;

public interface OrderService  {


    OrderDTO createOrderFromCart(Long customerId);

    List<OrderDTO> getOrderDetails(Long customerId);
}
