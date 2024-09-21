package com.pooja.book_ecommerce.controller;

import com.pooja.book_ecommerce.entity.Book;
import com.pooja.book_ecommerce.entity.Customer;
import com.pooja.book_ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer= customerService.getCustomerById(id);
        return new ResponseEntity<Customer>(customer, HttpStatus.FOUND);
    }


    @PostMapping
    public ResponseEntity<Customer> creatCustomer(@Valid @RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<Customer>(savedCustomer, HttpStatus.ACCEPTED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@Valid @PathVariable Long id, @RequestBody Customer customer) {
        Customer updatedCustomer=customerService.update(id,customer);
        return new ResponseEntity<Customer>(updatedCustomer,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        String message=customerService.deleteCustomer(id);
        return new ResponseEntity(message,HttpStatus.OK);
    }

}
