package com.ecommerce.order.client;

import com.ecommerce.order.model.HttpResponse;
import com.ecommerce.order.record.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service", url = "${application.config.payment-url}")
public interface PaymentClient {
    @PostMapping
    ResponseEntity<HttpResponse> requestOrderPayment(@RequestBody PaymentRequest paymentRequest);
}
