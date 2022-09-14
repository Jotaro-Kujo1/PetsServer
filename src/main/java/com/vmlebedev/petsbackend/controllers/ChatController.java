package com.vmlebedev.petsbackend.controllers;

import com.vmlebedev.petsbackend.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

import org.apache.camel.component.kafka.KafkaConstants;


@RestController
public class ChatController {
    private KafkaTemplate<String,Message> kafkaTemplate;

    @Autowired
    public ChatController(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping(value = "/api/send", consumes = "application/json", produces = "application/json")
    public void sendMessage(@RequestBody Message message) {
        message.setTimestamp(LocalDateTime.now().toString());
        kafkaTemplate.send("quickstart-events",message);
    }
}
