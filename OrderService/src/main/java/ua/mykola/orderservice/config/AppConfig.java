package ua.mykola.orderservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ua.mykola.commons.model.KafkaTopics;
import ua.mykola.commons.model.Order;


@Configuration
public class AppConfig {
    private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public NewTopic ordersTopic() {
        LOG.info("Creating Kafka topic: " + KafkaTopics.ORDER_TOPIC_NAME);
        return new NewTopic(
                KafkaTopics.ORDER_TOPIC_NAME,
                KafkaTopics.ORDER_TOPIC_NUM_PARTITIONS,
                (short) KafkaTopics.ORDER_TOPIC_REPLICATION_FACTOR);
    }

    @Bean
    public ProducerFactory<String, Order> producerFactory(KafkaProperties kafkaProperties) {
        var props = kafkaProperties.buildProducerProperties();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        props.put(ProducerConfig.ACKS_CONFIG, "all");

        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, Order> kafkaTemplate(ProducerFactory<String, Order> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
