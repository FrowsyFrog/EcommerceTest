package com.upc.ecommerce.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.upc.ecommerce.dto.OrderRequest;
import com.upc.ecommerce.entities.Order;
import com.upc.ecommerce.entities.OrderDetail;
import com.upc.ecommerce.repository.OrderRepository;
import com.upc.ecommerce.util.OrderStatus;
import com.upc.ecommerce.util.OrderValidator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public Order getOrderById(String orderId){
        return orderRepository.findOrderByOrderId(orderId);
    }
  
    @Transactional(readOnly = true)
    public List<Order> getAllOrderByAccountId(String accountId){
        return orderRepository.findOrdersByAccountId(accountId);
    }

    @Transactional
    public Order createOrder(OrderRequest orderRequest){
        OrderValidator.validateOrder(orderRequest);
        Order orderNew = initOrder(orderRequest);
        return orderRepository.save(orderNew);
    }

    private Order initOrder(OrderRequest orderRequest){
        Order orderObj = new Order();
        // Order
        orderObj.setOrderId(UUID.randomUUID().toString());
        orderObj.setAccountId(orderRequest.getAccountId());
        orderObj.setStatus(OrderStatus.PENDING);

        //Details
        List<OrderDetail> orderDetails = orderRequest.getItems()
        .stream()
        .map(
            item->OrderDetail.builder()
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .upc(item.getUpc())
                .tax((item.getPrice()*item.getQuantity())*0.16)
                .totalAmount((item.getPrice()*item.getQuantity()))
                .order(orderObj).build())
            .collect(Collectors.toList());

        orderObj.setOrderDetails(orderDetails);
        orderObj.setTotalAmount(orderDetails.
                    stream().mapToDouble(OrderDetail::getTotalAmount).sum());

        return orderObj;
    }
}
