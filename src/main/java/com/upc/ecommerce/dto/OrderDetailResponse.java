package com.upc.ecommerce.dto;

import lombok.Data;

@Data
public class OrderDetailResponse {
    private Long id;
    private Integer quantity;
    private Double price;
    private Double tax;
    private String upc;
    private Double totalAmount;
}
