package com.example.OrderManagementSystem.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OrderManagementSystem.Entity.Customer;
import com.example.OrderManagementSystem.Exception.CustomerNotFoundException;
import com.example.OrderManagementSystem.Repository.CustomerRepository;
import com.example.OrderManagementSystem.Service.CustomerService;

@Service
public class CustomerImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customers) {
        Customer customer = customerRepository.save(customers);
        return customer;
    }

    @Override
    public Customer getCustomerId(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                    () -> new CustomerNotFoundException("Customer not found with id : " + id));
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer customerDetail = customerRepository.findById(id).orElseThrow(
                    () -> new CustomerNotFoundException("Customer not found with id : " + id));
        customerDetail.setName(customer.getName());
        customerDetail.setEmail(customer.getEmail());
        customerDetail.setPassword(customer.getPassword());
        return customerRepository.save(customerDetail);
    }
    
}
