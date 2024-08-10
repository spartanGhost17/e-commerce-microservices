package com.ecommerce.customer.model;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class Address {
    private String street;
    private String houseNumber;
    private String city;
    private String zipCode;
}
