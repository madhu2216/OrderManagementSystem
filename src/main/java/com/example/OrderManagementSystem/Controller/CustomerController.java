package com.example.OrderManagementSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.OrderManagementSystem.Entity.Customer;
import com.example.OrderManagementSystem.Service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customerController")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;
    
    @PostMapping("/Post")
    public ResponseEntity<Customer> saveCustomer(@RequestBody @Valid Customer customers) {
        Customer customer = customerService.save(customers);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping("/getCustomerId/{id}")
    public ResponseEntity<Customer> getCustomerId(@PathVariable Long id) {
        Customer customer = customerService.getCustomerId(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

}
