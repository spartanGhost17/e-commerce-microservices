package com.ecommerce.order.mapper;

import com.ecommerce.order.dto.OrderDto;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.record.OrderRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest orderRequest) {
        return Order.builder()
                .id(orderRequest.id())
                .customerId(orderRequest.customerId())
                .reference(orderRequest.reference())
                .totalAmount(orderRequest.amount())
                .paymentMethod(orderRequest.paymentMethod())
                .build();
    }

    public OrderDto toOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getCustomerId(),
                //order.getOrderLines(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getLastModifiedAt()
        );
    }
}
