package org.nisum.kafka.producer.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Slf4j
public class EventProducer {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.producer.topic}")
    private String topic;


    public void orderEvent(String value) {
        ProducerRecord<String, String> orderRecord = new ProducerRecord<String, String>(topic, value);
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(orderRecord);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                handleFailure(value, ex);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                handleSuccess(value, result);
            }
        });

    }

    private void handleFailure(String value, Throwable ex) {
        log.error("Error Sending the Message and the exception is {}", ex.getMessage());
    }

    private void handleSuccess(String value, SendResult<String, String> result) {
        log.info("Message Sent SuccessFully for  value is {} , partition is {}", value, result.getRecordMetadata().partition());
    }
}
