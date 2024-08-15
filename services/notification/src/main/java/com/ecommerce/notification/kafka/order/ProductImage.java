package com.ecommerce.notification.kafka.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductImage(
        Integer id,
        String imageUrl
) {
}
