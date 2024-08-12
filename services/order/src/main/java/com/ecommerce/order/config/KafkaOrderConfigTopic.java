package com.ecommerce.order.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static com.ecommerce.order.constants.ApplicationConstants.ORDER_TOPIC;

@Configuration
public class KafkaOrderConfigTopic {

    @Bean
    public NewTopic OrderTopic() {
        return TopicBuilder.name(ORDER_TOPIC).build();
    }
}
