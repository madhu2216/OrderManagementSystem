package com.example.OrderManagementSystem.orderDTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private Long customerId;
    private List<ProductOrderRequest> products;
    
}
