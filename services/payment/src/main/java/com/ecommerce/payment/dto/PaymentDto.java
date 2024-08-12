package com.ecommerce.payment.dto;

import com.ecommerce.payment.enums.PaymentMethod;
import com.ecommerce.payment.record.Customer;

import java.math.BigDecimal;

public record PaymentDto(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId
) {
}
