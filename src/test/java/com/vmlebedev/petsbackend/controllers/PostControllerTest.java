package com.vmlebedev.petsbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmlebedev.petsbackend.models.Post;
import com.vmlebedev.petsbackend.services.PostService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;


    @Test
    void createPost() throws Exception{
        Post post = new Post();
        when(postService.checkPost(post)).thenReturn(true);
        when(postService.savePost(post)).thenReturn(post);

        mockMvc.perform(post("/posts/createPost")
                    .accept(MediaType.APPLICATION_JSON)
                    .content(asJsonString(post))
                    .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void getAllLostPosts() throws Exception{
        when(postService.findAllByState(true)).thenReturn(Arrays.asList(
                new Post(),
                new Post()
        ));
        mockMvc.perform(get("/posts/getAllPosts/lost"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)));
    }

    @Test
    void getAllSearchedPosts() throws Exception{
        when(postService.findAllByState(false)).thenReturn(Arrays.asList(
                new Post(),
                new Post()
        ));
        mockMvc.perform(get("/posts/getAllPosts/searched"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)));
    }

    @Test
    void getAllLostForArea() throws Exception{
        Post post1 = new Post();
        Post post2 = new Post();
        post1.setArea("Зашекснинский");
        post2.setArea("Зашекснинский");

        when(postService.findAllByStateAndArea(true,"Зашекснинский")).thenReturn(Arrays.asList(
                post1,
                post2
        ));
        mockMvc.perform(get("/posts/getLostForArea/Зашекснинский"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)));
    }

    @Test
    void getAllSearchedForArea() throws Exception{
        Post post1 = new Post();
        Post post2 = new Post();
        post1.setArea("Заягорбский");
        post2.setArea("Заягорбский");

        when(postService.findAllByStateAndArea(false,"Заягорбский")).thenReturn(Arrays.asList(
                post1,
                post2
        ));
        mockMvc.perform(get("/posts/getSearchedForArea/Заягорбский"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)));
    }

    @Test
    void getAllUsersPosts() throws Exception{
        Post post1 = new Post();
        Post post2 = new Post();

        post1.setLogin("Vadim");
        post2.setLogin("Vadim");

        when(postService.findAllUsersPosts("Vadim")).thenReturn(Arrays.asList(
                post1,
                post2
        ));
        mockMvc.perform(get("/posts/getAllUsersPosts/Vadim"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)));
    }


    private String asJsonString(final Object obj) {
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}