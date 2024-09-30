package org.nisum.kafka.producer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.nisum.kafka.producer.producer.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishController {

    @Autowired
    EventProducer orderEventProducer;

    @PostMapping("/v1/publish")
    public ResponseEntity<String> postLibraryEvent(@RequestBody String payload) throws JsonProcessingException {

        orderEventProducer.orderEvent(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body("Success");
    }
}
