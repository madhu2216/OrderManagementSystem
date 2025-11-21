package com.example.OrderManagementSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.OrderManagementSystem.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

    List<Order> findByCustomerId(Long customerId);

    @Query(value = "SELECT customer_id, COUNT(*) FROM orders GROUP BY customer_id", nativeQuery = true)
    List<Object[]> getOrderCountByCustomer();

    @Query(value = "SELECT customer_id, COUNT(*) AS total_orders FROM orders GROUP BY customer_id ORDER BY total_orders DESC LIMIT 5", 
       nativeQuery = true)
    List<Object[]> getTop5Customers();

    
}
