package com.ecommerce.order.controller;

import com.ecommerce.order.dto.OrderDto;
import com.ecommerce.order.model.HttpResponse;
import com.ecommerce.order.record.OrderRequest;
import com.ecommerce.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<HttpResponse> createOrder(@RequestBody @Valid OrderRequest orderRequest) {

        return new ResponseEntity<>(
          HttpResponse.builder()
                  .timeStamp(Instant.now().toString())
                  .message("Your order has been placed")
                  .data(Map.of("order", orderService.createOrder(orderRequest)))
                  .status(HttpStatus.CREATED)
                  .statusCode(HttpStatus.CREATED.value())
                  .build(), HttpStatus.CREATED
        );
    }

    @GetMapping
    ResponseEntity<HttpResponse> findAllOrders() {
        return new ResponseEntity<>(
                HttpResponse.builder()
                        .timeStamp(Instant.now().toString())
                        .message("Retrieving all orders")
                        .status(HttpStatus.OK)
                        .data(Map.of("orders", orderService.findAllOrders()))
                        .statusCode(HttpStatus.OK.value())
                        .build(), HttpStatus.OK);
    }

    @GetMapping(value = "/{orderId}")
    public ResponseEntity<HttpResponse> findOrderById(@PathVariable("orderId") Integer orderId) {
        return new ResponseEntity<>(
                HttpResponse.builder()
                        .timeStamp(Instant.now().toString())
                        .message("Retrieving order by id")
                        .status(HttpStatus.OK)
                        .data(Map.of("order", orderService.findById(orderId)))
                        .statusCode(HttpStatus.OK.value())
                        .build(), HttpStatus.OK);
    }
}
