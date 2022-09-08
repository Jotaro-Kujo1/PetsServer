package com.vmlebedev.petsbackend.controllers;

import com.vmlebedev.petsbackend.models.Conversation;
import com.vmlebedev.petsbackend.services.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/conversation")
@CrossOrigin
public class ConversationController {

    private ConversationService conversationService;

    @Autowired
    public ConversationController(ConversationService conversationService){
        this.conversationService = conversationService;
    }

    @PostMapping(value = "/saveConversation")
    public ResponseEntity<Conversation> createConversation(@RequestBody Conversation conversation){
        if(conversationService.checkConversation(conversation)){
            Conversation newConversation = conversationService.saveConversation(conversation);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path(newConversation.getId())
                    .buildAndExpand(newConversation.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }else {
            return ResponseEntity.status(404).build();
        }
    }
}
