package com.example.OrderManagementSystem.Service;

import java.util.List;

import com.example.OrderManagementSystem.Entity.Customer;

public interface CustomerService {

    Customer save(Customer customers);

    Customer getCustomerId(Long id);

    List<Customer> getAllCustomers();

    Customer updateCustomer(Long id, Customer customer);

    
} 