package com.ecommerce.payment.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static com.ecommerce.payment.constants.ApplicationConstants.PAYMENT_TOPIC;

@Configuration
public class KafkaPaymentTopicConfiguration {

    @Bean
    public NewTopic paymentTopic() {
        return TopicBuilder.name(PAYMENT_TOPIC).build();
    }
}
