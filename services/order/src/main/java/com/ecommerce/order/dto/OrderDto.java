package com.ecommerce.order.dto;

import com.ecommerce.order.enums.OrderStatus;
import com.ecommerce.order.model.OrderLine;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(
    Integer id,
    String reference,
    BigDecimal totalAmount,
    String customerId,
    //List<OrderLine> orderLines,
    OrderStatus status,
    LocalDateTime createdAt,
    LocalDateTime lastModifiedAt
) {
}
