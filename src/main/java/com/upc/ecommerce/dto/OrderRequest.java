package com.upc.ecommerce.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderRequest {
    
    @NotBlank
    @NotNull
    private String accountId;
    @NotNull
    private List<LineItem> items;

}
