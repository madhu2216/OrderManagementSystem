package com.example.OrderManagementSystem.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OrderManagementSystem.Entity.Product;
import com.example.OrderManagementSystem.Exception.ProductNotFoundException;
import com.example.OrderManagementSystem.Repository.ProductRepository;
import com.example.OrderManagementSystem.Service.ProductService;

@Service
public class ProductImpl implements ProductService{
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product store(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                        () -> new ProductNotFoundException("Product not found with id :" + id));
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product productUpdate(Long id, Product product) {
        Product updatedProduct = productRepository.findById(id).orElseThrow(
                        () -> new ProductNotFoundException("Product not found with id :" + id));
        updatedProduct.setName(product.getName());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setStock(product.getStock());
        return productRepository.save(updatedProduct);
    }
    
}
