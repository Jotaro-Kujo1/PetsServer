package com.vmlebedev.petsbackend.kafkaListeners;

import com.vmlebedev.petsbackend.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    private SimpMessagingTemplate template;



    @Autowired
    public MessageListener(SimpMessagingTemplate template){
        this.template = template;
    }

    @KafkaListener(
            topics = "quickstart-events",
            groupId = "com.vmlebedev"
    )
    public void listen(Message message) {
        System.out.println(" Send from kafka listener ");
        template.convertAndSend("/topic/group", message);
    }

}
