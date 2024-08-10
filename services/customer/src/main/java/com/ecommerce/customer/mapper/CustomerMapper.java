package com.ecommerce.customer.mapper;

import com.ecommerce.customer.dto.CustomerDto;
import com.ecommerce.customer.model.Customer;
import com.ecommerce.customer.record.CustomerRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        //customer Customer = new Customer();
        //return BeanUtils.copyProperties(request, customer);
        if (request == null) return null;

        return Customer.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .address(request.address())
                .email(request.email())
                .build();
    }

    public CustomerDto fromCustomer(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
