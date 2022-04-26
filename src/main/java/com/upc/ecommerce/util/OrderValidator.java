package com.upc.ecommerce.util;

import com.upc.ecommerce.dto.OrderRequest;
import com.upc.ecommerce.exception.IncorrectOrderRequestException;

public class OrderValidator {
    public static boolean validateOrder(OrderRequest order){
        if(order.getItems()==null || order.getItems().isEmpty()){
            throw new IncorrectOrderRequestException("Order Detail sin contenido");
        }
        return true;
    }
}
