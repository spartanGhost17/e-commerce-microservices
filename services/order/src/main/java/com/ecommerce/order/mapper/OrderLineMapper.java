package com.ecommerce.order.mapper;


import com.ecommerce.order.dto.OrderLineDto;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.model.OrderLine;
import com.ecommerce.order.record.OrderLineRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {


    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .quantity(orderLineRequest.quantity())
                .order(Order.builder()
                        .id(orderLineRequest.orderId())
                        .build())
                .productId(orderLineRequest.productId())
                .build();
    }

    public OrderLineDto toOrderLineDto(OrderLine orderLine) {
        return new OrderLineDto(
                orderLine.getId(),
                orderLine.getProductId(),
                orderLine.getQuantity()
        );
    }
}
