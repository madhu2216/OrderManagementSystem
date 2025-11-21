package com.example.OrderManagementSystem.Controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.OrderManagementSystem.Entity.CustomerOrderReport;
import com.example.OrderManagementSystem.Entity.Order;
import com.example.OrderManagementSystem.Service.OrderService;
import com.example.OrderManagementSystem.Service.Impl.ReportingServiceImpl;
import com.example.OrderManagementSystem.orderDTO.OrderRequest;

@RestController
@RequestMapping("/orderController/")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ReportingServiceImpl reportingService;

    @PostMapping("/postOrder")
    public ResponseEntity<Order> postOrder(@RequestBody OrderRequest orderRequest) {
        Order order = orderService.postOrder(orderRequest);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable Long customerId) {
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/amount/{orderId}")
    public ResponseEntity<BigDecimal> getTotalAmount(@PathVariable Long orderId) {
        BigDecimal totalAmt = orderService.getTotalAmount(orderId);
        return ResponseEntity.ok(totalAmt);
    }

    @GetMapping("/customer-order-count")
    public List<CustomerOrderReport> fetchOrderCount() {
        return reportingService.getReport();
    }

    @GetMapping("/top-customers")
    public List<CustomerOrderReport> fetchTopCustomers() {
        return reportingService.getTopCustomers();
    }

}
