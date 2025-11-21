package com.example.OrderManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OrderManagementSystem.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

    
} 
