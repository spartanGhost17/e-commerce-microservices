package com.ecommerce.customer.dto;

import com.ecommerce.customer.model.Address;

public record CustomerDto(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
