package com.upc.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LineItem {
    private String upc;
    private Integer quantity;
    private Double price;
}
