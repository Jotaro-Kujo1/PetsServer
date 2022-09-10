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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void createConversation() throws Exception{
        Conversation conversation = new Conversation("testId","testSender","testReceiver","testImg");
        when(conversationService.checkConversation(conversation)).thenReturn(true);
        when(conversationService.saveConversation(conversation)).thenReturn(conversation);

        mockMvc.perform(post("/conversation/saveConversation")
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(conversation))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    private String asJsonString(final Object obj) {
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}