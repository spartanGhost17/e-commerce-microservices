package com.ecommerce.order.record;

import com.ecommerce.order.dto.CustomerDto;
import com.ecommerce.order.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerDto customer
) {
}
