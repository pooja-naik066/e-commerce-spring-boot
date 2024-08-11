package com.pooja.book_ecommerce.service;

import com.pooja.book_ecommerce.entity.Customer;

import java.util.List;

public interface CustomerService {


    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id);

    Customer saveCustomer(Customer customer);

    Customer update(Long id, Customer customer);

    String deleteCustomer(Long id);
}
