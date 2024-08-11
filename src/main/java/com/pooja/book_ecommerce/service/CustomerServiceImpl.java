package com.pooja.book_ecommerce.service;

import com.pooja.book_ecommerce.entity.Cart;
import com.pooja.book_ecommerce.entity.Customer;
import com.pooja.book_ecommerce.exception.BookNotFoundException;
import com.pooja.book_ecommerce.exception.CustomerNotFoundException;
import com.pooja.book_ecommerce.repository.CartRepository;
import com.pooja.book_ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CartRepository cartRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,CartRepository cartRepository){
        this.customerRepository=customerRepository;
        this.cartRepository=cartRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        Optional<Customer> customer= customerRepository.findById(id);
        if(customer.isPresent()){
            return customer.get();
        }
        else
            throw new CustomerNotFoundException("Customer with id "+id+" not found");
    }

    public Customer saveCustomer(Customer customer) {
        Cart cart=new Cart();
        cart.setCustomer(customer);
        cartRepository.save(cart);
        return customerRepository.save(customer);
    }


    public Customer update(Long id, Customer customer) {
        Optional<Customer> tempCustomer = customerRepository.findById(id);
        if(tempCustomer.isPresent()){
            Customer savedCustomer= customerRepository.save(customer);
            return savedCustomer;
        }
        else
            throw new CustomerNotFoundException("Customer with id "+id+" not found");
    }

    public String deleteCustomer(Long id) {
        Optional<Customer> tempCustomer= customerRepository.findById(id);
        if(tempCustomer.isPresent()){
            customerRepository.deleteById(id);
            return "Deleted customer with id "+id+" ";
        }
        else
            throw new BookNotFoundException("Customer with id "+id+" not found");
    }
}
