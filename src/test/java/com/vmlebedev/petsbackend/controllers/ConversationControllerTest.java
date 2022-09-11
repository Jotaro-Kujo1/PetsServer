package com.vmlebedev.petsbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmlebedev.petsbackend.models.Conversation;
import com.vmlebedev.petsbackend.services.ConversationService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@WebMvcTest(ConversationController.class)
class ConversationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConversationService conversationService;

    @Test
    public void createConversation() throws Exception{
        Conversation conversation = new Conversation("testId","testSender","testReceiver","testImg");
        when(conversationService.checkConversation(conversation)).thenReturn(true);
        when(conversationService.saveConversation(conversation)).thenReturn(conversation);

        mockMvc.perform(post("/conversation/saveConversation")
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(conversation))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }


    @Test
    public void getConversations() throws Exception{
        List<Conversation> list = new ArrayList<>();
        when(conversationService.getAllUsersConversations("testSender1")).thenReturn(Arrays.asList(
                new Conversation("testId1", "testSender1", "testReceiver1", "testProfimg1"),
                new Conversation("testId2", "testSender1", "testReceiver2", "testProfimg2"),
                new Conversation("testId3", "testSender1", "testReceiver3", "testProfimg3")
        ));

        mockMvc.perform(get("/conversation/getUsersConversations?sender_login=testSender1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)));
    }



    private String asJsonString(final Object obj) {
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}