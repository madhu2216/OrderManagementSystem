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

import com.example.OrderManagementSystem.Entity.Product;
import com.example.OrderManagementSystem.Service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ProductController")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @PostMapping("/productStore")
    public ResponseEntity<Product> productStore(@RequestBody @Valid Product product) {
        Product productSaved = productService.store(product);
        return new ResponseEntity<>(productSaved, HttpStatus.CREATED);
    }

    @GetMapping("/getProductId/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> product = productService.getAllProducts();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Product> productUpdate(@PathVariable Long id, @RequestBody Product product) {
        Product productUpdated = productService.productUpdate(id, product);
        return new ResponseEntity<>(productUpdated, HttpStatus.OK);
    }
}
