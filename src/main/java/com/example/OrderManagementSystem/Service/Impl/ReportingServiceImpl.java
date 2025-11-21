package com.example.OrderManagementSystem.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OrderManagementSystem.Entity.CustomerOrderReport;
import com.example.OrderManagementSystem.Repository.OrderRepository;

@Service
public class ReportingServiceImpl {
    
    @Autowired
    private OrderRepository orderRepository;

    public List<CustomerOrderReport> getReport() {
        List<Object[]> result = orderRepository.getOrderCountByCustomer();

        return result.stream().map(res -> new CustomerOrderReport(
                ((Number) res[0]).longValue(),
                ((Number) res[1]).intValue()))
            .toList();

    }


}
