package com.ecommerce.payment.controller;

import com.ecommerce.payment.model.HttpResponse;
import com.ecommerce.payment.record.PaymentRequest;
import com.ecommerce.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<HttpResponse> createPayment(@RequestBody @Valid PaymentRequest request) {
        return new ResponseEntity<>(HttpResponse.builder()
                .timeStamp(Instant.now().toString())
                .message("Created Payment")
                .data(Map.of("payment", paymentService.createPayment(request)))
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build(), HttpStatus.CREATED);
    }
}
