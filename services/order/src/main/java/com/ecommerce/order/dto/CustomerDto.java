package com.ecommerce.order.dto;

public record CustomerDto(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
