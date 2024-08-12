package com.ecommerce.notification.kafka.order;

import java.math.BigDecimal;
import java.util.List;

public record Product(
        Integer id,
        String name,
        String description,
        BigDecimal price,
        double quantity,
        List<ProductImage> images
) {
}
