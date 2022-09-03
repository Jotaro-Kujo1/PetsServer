package com.vmlebedev.petsbackend.services;

import com.vmlebedev.petsbackend.models.Post;
import com.vmlebedev.petsbackend.repository.PostRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@WebMvcTest(PostService.class)
class PostServiceTest {

    @Autowired
    private PostService postService;

    @MockBean
    private PostRepository postRepository;

    @Test
    void findAll() {
        List<Post> postsFromMock = new ArrayList<Post>();
        when(postRepository.findAll()).thenReturn(postsFromMock);
        List<Post> posts = postService.findAll();
        Assert.assertEquals(posts,postsFromMock);
    }

    @Test
    void findAllByArea() {
        List<Post> postFromMock = new ArrayList<Post>();
        when(postRepository.findAllByArea("Зашекснинский")).thenReturn(postFromMock);
        List<Post> posts = postService.findAllByArea("Зашекснинский");
        Assert.assertEquals(posts,postFromMock);
    }

    @Test
    void findAllUsersPosts() {
        List<Post> postFromMock = new ArrayList<Post>();
        when(postRepository.findAllByLogin("Test")).thenReturn(postFromMock);
        List<Post> posts = postService.findAllUsersPosts("Test");
        Assert.assertEquals(posts,postFromMock);
    }


    @Test
    void findAllByState() {
        List<Post> postFromMock = new ArrayList<Post>();
        when(postRepository.findAllByState(true)).thenReturn(postFromMock);
        List<Post> posts = postService.findAllByState(true);
        Assert.assertEquals(posts,postFromMock);
    }

    @Test
    void findAllByStateAndArea() {
        List<Post> postFromMock = new ArrayList<Post>();
        when(postRepository.findAllByStateAndArea(true,"Зашекснинский")).thenReturn(postFromMock);
        List<Post> posts = postService.findAllByStateAndArea(true,"Зашекснинский");
        Assert.assertEquals(posts,postFromMock);
    }

}