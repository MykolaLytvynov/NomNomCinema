package ua.mykola.orderservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ua.mykola.commons.model.KafkaTopics;
import ua.mykola.commons.model.Order;

@Service
public class OrderSender {
    private static final Logger LOG = LoggerFactory.getLogger(OrderSender.class);
    private final KafkaTemplate<String, Order> producer;

    public OrderSender(KafkaTemplate<String, Order> producer) {
        this.producer = producer;
    }

    public void send(Order order) {
        producer.send(KafkaTopics.ORDER_TOPIC_NAME, order)
                .whenComplete(
                        (result, ex) -> {
                            if (ex == null) {
                                LOG.info("Message #{} was sent, offset:{}",
                                        order,
                                        result.getRecordMetadata().offset());
                            } else {
                                LOG.error("Message #{} was not sent", order, ex);
                            }
                        });
    }
}
