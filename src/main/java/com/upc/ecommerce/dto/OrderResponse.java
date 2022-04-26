package com.upc.ecommerce.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderResponse {

    private String orderId;
    private String status;
    private String accountId;
    private Double totalAmount;
    private Double totalTax;
    private Double totalAmountTax;
    private Data transactionDate;
    List<OrderDetailResponse> details;
}
