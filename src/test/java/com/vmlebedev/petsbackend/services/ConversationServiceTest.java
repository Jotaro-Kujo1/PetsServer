package com.vmlebedev.petsbackend.services;

import com.vmlebedev.petsbackend.models.Conversation;
import com.vmlebedev.petsbackend.models.Post;
import com.vmlebedev.petsbackend.repository.ConversationRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@WebMvcTest(ConversationService.class)
class ConversationServiceTest {

    @Autowired
    private ConversationService conversationService;

    @MockBean
    private ConversationRepository conversationRepository;

    @Test
    void saveConversation() {
        Conversation conversation = new Conversation("testId","testSender","testReceiver","testImg");
        when(conversationRepository.save(conversation)).thenReturn(conversation);
        Conversation testConversation = conversationRepository.save(conversation);
        Assert.assertEquals(conversation,testConversation);
    }

    @Test
    void findAll() {
        List<Conversation> convFromMock = new ArrayList<Conversation>();
        when(conversationRepository.findAll()).thenReturn(convFromMock);
        List<Conversation> conversations = conversationService.findAll();
        Assert.assertEquals(conversations,convFromMock);
    }

    @Test
    public void getConversations() {
        when(conversationRepository.findAllBySenderLogin("testSender1")).thenReturn(Arrays.asList(
                new Conversation("testId1", "testSender1", "testReceiver1", "testProfimg1"),
                new Conversation("testId2", "testSender1", "testReceiver2", "testProfimg2"),
                new Conversation("testId3", "testSender1", "testReceiver3", "testProfimg3")
        ));
        List<Conversation> list = conversationRepository.findAllBySenderLogin("testSender1");
        Assertions.assertEquals(list.size(),3);
    }

}