package com.ecommerce.order.controller;

import com.ecommerce.order.model.HttpResponse;
import com.ecommerce.order.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/order-line")
@RequiredArgsConstructor
public class OrderLineController {

    private final OrderLineService orderLineService;

    @GetMapping("/order/{order-id}")
    public ResponseEntity<HttpResponse> findByOrderId(@PathVariable("order-id") Integer orderId) {
        return new ResponseEntity<>(
                HttpResponse.builder()
                        .timeStamp(Instant.now().toString())
                        .message("Retrieving order line for order")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(Map.of("orderId", orderLineService.findAllByOrderId(orderId)))
                        .build(), HttpStatus.OK);
    }
}