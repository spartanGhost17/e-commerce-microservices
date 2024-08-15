package com.ecommerce.order.kafka;

import com.ecommerce.order.dto.CustomerDto;
import com.ecommerce.order.dto.PurchaseDto;
import com.ecommerce.order.enums.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerDto customer,
        List<PurchaseDto> products
) {
}
