package com.example.OrderManagementSystem.Service.Impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OrderManagementSystem.Entity.Customer;
import com.example.OrderManagementSystem.Entity.Order;
import com.example.OrderManagementSystem.Entity.Product;
import com.example.OrderManagementSystem.Exception.CustomerNotFoundException;
import com.example.OrderManagementSystem.Exception.OrderNotFoundException;
import com.example.OrderManagementSystem.Exception.ProductNotFoundException;
import com.example.OrderManagementSystem.Repository.CustomerRepository;
import com.example.OrderManagementSystem.Repository.OrderRepository;
import com.example.OrderManagementSystem.Repository.ProductRepository;
import com.example.OrderManagementSystem.Service.OrderService;
import com.example.OrderManagementSystem.orderDTO.OrderItem;
import com.example.OrderManagementSystem.orderDTO.OrderRequest;
import com.example.OrderManagementSystem.orderDTO.ProductOrderRequest;

@Service
public class OrderImpl implements OrderService{

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order postOrder(OrderRequest orderRequest) {

        Customer customer = customerRepository.findById(orderRequest.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(
                    "Customer not found with id: " + orderRequest.getCustomerId()));

        Order order = new Order();
        order.setCustomer(customer);

        List<ProductOrderRequest> productRequests = orderRequest.getProducts();
        List<Long> productIds = productRequests.stream()
            .map(ProductOrderRequest::getProductId)
            .toList();

        List<Product> products = productRepository.findAllById(productIds);

        List<Long> foundIds = products.stream().map(Product::getId).toList();
        List<Long> invalid = productIds.stream()
                                   .filter(id -> !foundIds.contains(id))
                                   .toList();

        if (!invalid.isEmpty()) {
            throw new ProductNotFoundException("Invalid product IDs: " + invalid);
        }

        List<OrderItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (ProductOrderRequest req : productRequests) {
            Product product = products.stream()
                .filter(p -> p.getId().equals(req.getProductId()))
                .findFirst()
                .get();

            if (product.getStock() < req.getQuantity()) {
                throw new RuntimeException("Insufficient stock");
            }

            product.setStock(product.getStock() - req.getQuantity());

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(req.getQuantity());
            item.setPrice(product.getPrice());

            items.add(item);

            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(req.getQuantity())));
        }

        order.setItems(items);
        order.setTotalPrice(total);

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByCustomerId(Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundException("Customer not found with id: " + customerId);
        }
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public BigDecimal getTotalAmount(Long orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException("Order not found: " + orderId));
            
        return order.getTotalPrice();
    }
}
