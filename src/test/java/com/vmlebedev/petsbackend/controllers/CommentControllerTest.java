package com.vmlebedev.petsbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmlebedev.petsbackend.models.Comment;
import com.vmlebedev.petsbackend.models.Receiver;
import com.vmlebedev.petsbackend.services.CommentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CommentService commentService;

    @Test
    void createComment() throws Exception{
        Receiver receiver = new Receiver();
        receiver.setId("testId");
        receiver.setReceiverLogin("testLogin");
        when(commentService.checkIfExist(receiver)).thenReturn(true);
        when(commentService.saveReceiver(receiver)).thenReturn(receiver);


        mockMvc.perform(post("/comment/createComment")
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(receiver))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    @Test
    void getComments() throws Exception{
        when(commentService.getAllCommentsForUser("testLogin")).thenReturn(Arrays.asList(
                new Comment(),
                new Comment(),
                new Comment()
        ));

        mockMvc.perform(get("/comment/getComments?receiver_login=testLogin"))
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