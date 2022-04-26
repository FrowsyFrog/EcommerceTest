package com.upc.ecommerce.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.upc.ecommerce.dto.OrderRequest;
import com.upc.ecommerce.dto.OrderResponse;
import com.upc.ecommerce.entities.Order;
import com.upc.ecommerce.services.OrderService;
import com.upc.ecommerce.util.EntityDtoConverter;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;
    private EntityDtoConverter converter;
    
    public OrderController(OrderService orderService, EntityDtoConverter converter){
        this.orderService = orderService;
        this.converter = converter;
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<OrderResponse>> findAllOrders(@PathVariable String accountId) {
        List<Order> orders = orderService.getAllOrderByAccountId(accountId);
        return new ResponseEntity<List<OrderResponse>>(converter.converEntityToDto(orders), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> findOrderById(@PathVariable String orderId) {
        Order order = orderService.getOrderById(orderId);
        return new ResponseEntity<OrderResponse>(converter.converEntityToDto(order), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        Order order = orderService.createOrder(request);
        return new ResponseEntity<OrderResponse>(converter.converEntityToDto(order), HttpStatus.CREATED);
    }
}
