package com.ecommerce.customer.controller;

import com.ecommerce.customer.model.Customer;
import com.ecommerce.customer.model.HttpResponse;
import com.ecommerce.customer.record.CustomerRequest;
import com.ecommerce.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping(value="/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<HttpResponse> createCustomer(
            @RequestBody @Valid CustomerRequest request
    ) {
        String customerId = customerService.createCustomer(request);
        return new ResponseEntity<>(
                HttpResponse.builder()
                        .timeStamp(Instant.now().toString())
                        .message("customer created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .data(Map.of("customer", customerId))
                        .build(), HttpStatus.CREATED
        );
    }

    @PutMapping
    public ResponseEntity<HttpResponse> updateCustomer(
            @RequestBody @Valid CustomerRequest request
    ) {
        Customer customer = customerService.updateCustomer(request);

        return new ResponseEntity<>(
                HttpResponse.builder()
                        .timeStamp(Instant.now().toString())
                        .message("customer updated")
                        .status(HttpStatus.ACCEPTED)
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .data(Map.of("customer", customer))
                        .build(), HttpStatus.ACCEPTED
        );

    }

    @GetMapping
    public ResponseEntity<HttpResponse> findAll() {
        return new ResponseEntity<>(
                HttpResponse.builder()
                        .timeStamp(Instant.now().toString())
                        .message("find all customers")
                        .status(HttpStatus.ACCEPTED)
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .data(Map.of("customers", customerService.findAllCustomers()))
                        .build(), HttpStatus.ACCEPTED
        );
    }


    @GetMapping(value="/exists/{customerId}")
    public ResponseEntity<HttpResponse> existsById(@PathVariable("customerId") String customerId) {
        return new ResponseEntity<>(
                HttpResponse.builder()
                        .timeStamp(Instant.now().toString())
                        .message("costumer exists by id")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(Map.of("exists", customerService.existsById(customerId)))
                        .build(), HttpStatus.OK
        );
    }

    @GetMapping(value="/{customerId}")
    public ResponseEntity<HttpResponse> findById(@PathVariable("customerId") String customerId) {
        return new ResponseEntity<>(
                HttpResponse.builder()
                        .timeStamp(Instant.now().toString())
                        .message("found customer by id")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(Map.of("customer", customerService.findById(customerId)))
                        .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(value="/{customerId}")
    public ResponseEntity<HttpResponse> deleteById(@PathVariable("customerId") String customerId) {
        return new ResponseEntity<>(
                HttpResponse.builder()
                        .timeStamp(Instant.now().toString())
                        .message("Deleted Customer")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(Map.of("customer", customerService.deleteById(customerId)))
                        .build(), HttpStatus.OK
        );
    }



}
