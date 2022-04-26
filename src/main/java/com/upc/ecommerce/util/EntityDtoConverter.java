package com.upc.ecommerce.util;

import java.util.List;
import java.util.stream.Collectors;

import com.upc.ecommerce.dto.OrderResponse;
import com.upc.ecommerce.entities.Order;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoConverter {
    
    //Inyeccion de dependencia DI
    private ModelMapper modelMapper;

    public EntityDtoConverter(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public OrderResponse converEntityToDto(Order order){
        return modelMapper.map(order, OrderResponse.class);
    }

    public List<OrderResponse> converEntityToDto(List<Order> orders){
        return orders.stream()
        .map(order->converEntityToDto(order))
        .collect(Collectors.toList());
    }
}
