package ua.mykola.buffetservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ua.mykola.commons.model.KafkaTopics;
import ua.mykola.commons.model.Order;


@Service
public class OrderListener {
    private static final Logger LOG = LoggerFactory.getLogger(OrderListener.class);
    private final PrinterService printerService;

    public OrderListener(PrinterService printerService) {
        this.printerService = printerService;
    }

    @KafkaListener(topics = KafkaTopics.ORDER_TOPIC_NAME, containerFactory = "listenerContainerFactory")
    public void listen(@Payload Order order, @Header("kafka_offset") Long offset) {
        try {
            LOG.info("Received message: {}, offset: {}", order, offset);
            printerService.printOrder(order);
        } catch (Exception ex) {
            LOG.error("Error processing message: {}", order, ex);
        }
    }
}
