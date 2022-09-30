package com.vmlebedev.petsbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmlebedev.petsbackend.models.Raiting;
import com.vmlebedev.petsbackend.services.RaitingCounterService;
import jdk.jfr.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@WebMvcTest(RaitingCounterController.class)
class RaitingCounterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RaitingCounterService raitingCounterService;

    @Test
    void createRaitingIfNot() throws Exception{
        Raiting raiting = new Raiting("testId","test",null,null);
        when(raitingCounterService.checkIfExist(raiting)).thenReturn(true);
        when(raitingCounterService.saveRaiting(raiting)).thenReturn(raiting);

        mockMvc.perform(post("/raiting/createRaiting")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(raiting))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());
    }



    @Test
    void getLikesAmount() throws Exception{
        when(raitingCounterService.getLikesAmount("test")).thenReturn(1);

        mockMvc.perform(get("/raiting/getLikesAmount?login=test"))
                .andExpect(status().isOk());

    }

    private String asJsonString(final Object obj) {
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}