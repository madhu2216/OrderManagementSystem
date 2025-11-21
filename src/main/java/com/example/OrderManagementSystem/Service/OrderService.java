package com.example.OrderManagementSystem.Service;

import java.math.BigDecimal;
import java.util.List;

import com.example.OrderManagementSystem.Entity.Order;
import com.example.OrderManagementSystem.orderDTO.OrderRequest;

public interface OrderService {

    Order postOrder(OrderRequest orderRequest);

    List<Order> getOrdersByCustomerId(Long customerId);

    BigDecimal getTotalAmount(Long orderId);
    
} 