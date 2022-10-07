package com.vmlebedev.petsbackend.services;

import com.vmlebedev.petsbackend.models.Comment;
import com.vmlebedev.petsbackend.models.Receiver;
import com.vmlebedev.petsbackend.repository.CommentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@WebMvcTest(CommentService.class)
class CommentServiceTest {

    @Autowired
    private CommentService commentService;
    @MockBean
    private CommentRepository commentRepository;

    @Test
    void findAll() {
        when(commentRepository.findAll()).thenReturn(Arrays.asList(
                new Receiver(),
                new Receiver(),
                new Receiver()
        ));
        List<Receiver> foundList = (List<Receiver>) commentService.findAll();
        Assertions.assertEquals(foundList.size(),3);
    }

    @Test
    void saveReceiver() {
        Receiver receiver = new Receiver();
        receiver.setId("testId");
        receiver.setReceiverLogin("testLogin");
        receiver.setComments(Arrays.asList(new Comment()));
        when(commentRepository.save(receiver)).thenReturn(receiver);

        Receiver savedReceiver = commentRepository.save(receiver);
        Assertions.assertEquals(receiver.getReceiverLogin(),savedReceiver.getReceiverLogin());
    }



    @Test
    void getAllCommentsForUser() {
        Receiver receiver = new Receiver();
        receiver.setId("testId");
        receiver.setReceiverLogin("testLogin");
        receiver.setComments(Arrays.asList(new Comment()));

        when(commentRepository.findAllByReceiverLogin("testLogin")).thenReturn(receiver);

        Receiver newReceiver = commentRepository.findAllByReceiverLogin("testLogin");
        Assertions.assertEquals(receiver.getReceiverLogin(),newReceiver.getReceiverLogin());
    }

}