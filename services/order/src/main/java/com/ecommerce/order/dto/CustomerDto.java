package com.ecommerce.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CustomerDto(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
