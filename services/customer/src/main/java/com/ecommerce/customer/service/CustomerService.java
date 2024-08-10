package com.ecommerce.customer.service;

import com.ecommerce.customer.dto.CustomerDto;
import com.ecommerce.customer.exception.CustomerNotFoundException;
import com.ecommerce.customer.mapper.CustomerMapper;
import com.ecommerce.customer.model.Customer;
import com.ecommerce.customer.record.CustomerRequest;
import com.ecommerce.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        var customer = customerRepository.save(mapper.toCustomer(request));
        return customer.getId();
    }


    public Customer updateCustomer(CustomerRequest request) {
        var customer = customerRepository.findById(request.id()).orElseThrow(() -> new CustomerNotFoundException(
                String.format("Cannot update customer:: No customer found with the provided ID:: %s", request.id())
        ));
        mergerCustomer(customer, request);
        customerRepository.save(customer);
        return customer;
    }


    private void mergerCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstName())) {
            customer.setFirstName(request.firstName());
        }

        if(StringUtils.isNotBlank(request.lastName())) {
            customer.setLastName(request.lastName());
        }

        if(StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }

        if(!Objects.isNull(request.address())) {
            customer.setAddress(request.address());
        }
    }


    public List<CustomerDto> findAllCustomers() {
        return customerRepository.findAll().stream().map(mapper::fromCustomer).collect(Collectors.toList());
    }

    public boolean existsById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    public CustomerDto findById(String customerId) {
        return customerRepository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(
                () -> new CustomerNotFoundException(
                        String.format("Cannot find customer with the provided ID:: %s", customerId)
                )
        );
    }

    public CustomerDto deleteById(String customerId) {
        CustomerDto customerDto = findById(customerId);
        customerRepository.deleteById(customerId);
        return customerDto;
    }
}
