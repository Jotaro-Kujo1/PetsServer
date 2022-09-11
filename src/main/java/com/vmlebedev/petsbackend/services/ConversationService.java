package com.vmlebedev.petsbackend.services;

import com.vmlebedev.petsbackend.models.Conversation;
import com.vmlebedev.petsbackend.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ConversationService {
    private ConversationRepository conversationRepository;

    @Autowired
    public ConversationService(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    public Conversation saveConversation(Conversation conversation) {
        String uniqueKey = UUID.randomUUID().toString();
        conversation.setId(uniqueKey);
        return conversationRepository.save(conversation);
    }

    public List<Conversation> findAll() {
        return (List<Conversation>) conversationRepository.findAll();
    }


    public boolean checkConversation(Conversation conversation) {
        boolean check =
                findAll()
                        .stream()
                        .anyMatch(s -> (s.getSenderLogin().equals(conversation.getSenderLogin())) && (s.getReceiverLogin().equals(conversation.getReceiverLogin())));
        if(check){
            return false;
        }else return true;
    }

    public List<Conversation> getAllUsersConversations(String login){
        return conversationRepository.findAllBySenderLogin(login);
    }
}
