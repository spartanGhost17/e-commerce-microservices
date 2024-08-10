package com.ecommerce.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MongoHealthIndicator implements HealthIndicator {

    private final MongoTemplate mongoTemplate;

    @Override
    public Health health() {
        try {
            // Simple ping to check if MongoDB is up
            mongoTemplate.executeCommand("{ ping: 1 }");
            return Health.up().withDetail("MongoDB", "Database is up").build();
        } catch (Exception e) {
            return Health.down().withDetail("MongoDB", "Database is down").withException(e).build();
        }
    }
}
