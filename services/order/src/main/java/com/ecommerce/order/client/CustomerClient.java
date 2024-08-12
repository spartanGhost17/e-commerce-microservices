package com.ecommerce.order.client;

import com.ecommerce.order.model.HttpResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "${application.config.customer-url}")
public interface CustomerClient {

    @GetMapping("/{customerId}")
    ResponseEntity<HttpResponse> findCustomerById(@PathVariable("customerId") String customerId);
}
