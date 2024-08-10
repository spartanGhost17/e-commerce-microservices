package com.ecommerce.customer.controller;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        // Adding system time dynamically
        Map<String, Object> buildDetails = new HashMap<>();
        buildDetails.put("time", Instant.now().toString()); // Current system time

        builder.withDetail("build", buildDetails)
                .withDetail("app", Map.of(
                        "name", "Balanciaga shop",
                        "description", "This is a sample Spring Boot application.",
                        "version", "1.0.0"
                ))
                .withDetail("company", Map.of(
                        "name", "Balanciaga shop",
                        "contact", "support@balanciaga.com",
                        "website", "https://www.mycompany.com"
                ));
    }

}
