package com.example.OrderManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OrderManagementSystem.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

    
} 