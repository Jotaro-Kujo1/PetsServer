package com.vmlebedev.petsbackend.repository;

import com.vmlebedev.petsbackend.models.Conversation;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ConversationRepositoryTest {

    @Autowired
    private ConversationRepository conversationRepository;

    @Test
    public void saveConversationTest() {
        Conversation conversation = new Conversation("testId","testSender","testReceiver","testProfImg");
        Assertions.assertNotNull(conversationRepository.save(conversation));
    }

    @Test
    public void getConversationTest(){
        saveConversationTest();
        Optional<Conversation> conversation = conversationRepository.findById("testId");
        Assertions.assertNotNull(conversation);
    }


    @Test
    public void updateConversationTest() {
        saveConversationTest();
        Optional<Conversation> tmp = conversationRepository.findById("testId");
        Conversation newTmp = new Conversation();
        if(tmp.isPresent()) newTmp = tmp.get();
        newTmp.setSenderLogin("justNewLogin");
        Conversation change = conversationRepository.save(newTmp);
        Assertions.assertEquals(change.getSenderLogin(),"justNewLogin");
    }

}