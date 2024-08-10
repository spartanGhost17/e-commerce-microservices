package com.ecommerce.customer.record;

import com.ecommerce.customer.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(

        String id,
        @NotNull(message = "Customer first name is required")
        String firstName,
        @NotNull(message = "Customer last name is required")
        String lastName,
        @NotNull(message = "Email is required")
        @Email(message = "The provided email address is not valid")
        String email,
        Address address
) {

}
