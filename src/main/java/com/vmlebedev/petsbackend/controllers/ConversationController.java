package com.vmlebedev.petsbackend.controllers;

import com.vmlebedev.petsbackend.convertors.ConversationView2Conversation;
import com.vmlebedev.petsbackend.models.Conversation;
import com.vmlebedev.petsbackend.services.ConversationService;
import com.vmlebedev.petsbackend.views.ConversationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/conversation")
@CrossOrigin
public class ConversationController {

    private final ConversationService conversationService;
    private final ConversationView2Conversation converter;

    @Autowired
    public ConversationController(ConversationService conversationService,ConversationView2Conversation converter){
        this.conversationService = conversationService;
        this.converter = converter;
    }

    @RequestMapping(value = "/saveConversation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Conversation> createConversation(@RequestBody ConversationView conversationView){
        Conversation conversation = converter.convert(conversationView);
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

    @GetMapping(value = "/getUsersConversations")
    public ResponseEntity<Iterable<Conversation>> getAllUsersConversations(@RequestParam String sender_login){
        return ResponseEntity.ok(conversationService.getAllUsersConversations(sender_login));
    }
}
