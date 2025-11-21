package com.example.OrderManagementSystem.Service;

import java.util.List;

import com.example.OrderManagementSystem.Entity.Product;

public interface ProductService {

    Product store(Product product);

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product productUpdate(Long id, Product product);
    
}
