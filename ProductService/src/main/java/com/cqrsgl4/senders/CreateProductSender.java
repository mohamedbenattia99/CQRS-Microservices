package com.cqrsgl4.senders;

import com.cqrsgl4.events.ProductCreatedEvent;
import com.cqrsgl4.queues.CreateProductQueue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
public class CreateProductSender {
    static final String ROUTING_KEY = "create.product.";

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    public CreateProductSender(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(ProductCreatedEvent productCreatedEvent) throws JsonProcessingException {
        String productCreatedEventJson = this.objectMapper.writeValueAsString(productCreatedEvent);
        Message message = MessageBuilder
                .withBody(productCreatedEventJson.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();
        this.rabbitTemplate.convertAndSend(
                CreateProductQueue.QUEUE_NAME,
                ROUTING_KEY + productCreatedEvent.getProductId(),
                message
        );
    }
}