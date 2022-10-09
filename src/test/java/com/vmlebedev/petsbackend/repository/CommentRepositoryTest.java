package com.vmlebedev.petsbackend.repository;

import com.vmlebedev.petsbackend.models.Comment;
import com.vmlebedev.petsbackend.models.Conversation;
import com.vmlebedev.petsbackend.models.Receiver;
import com.vmlebedev.petsbackend.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void saveConversationTest() {
        Receiver receiver = new Receiver();
        Assertions.assertNotNull(commentRepository.save(receiver));
    }

    @Test
    public void getConversationTest(){
        init();
        Optional<Receiver> receiver = commentRepository.findById("testId1");
        Assertions.assertNotNull(receiver);
    }

    @Test
    public void updateUser(){
        init();
        Receiver tmp = new Receiver();
        Optional<Receiver> comment = commentRepository.findById("testId1");
        if(comment.isPresent()) tmp = comment.get();
        tmp.setReceiverLogin("newTestLogin1");
        Receiver changeReceiver = commentRepository.save(tmp);
        Assertions.assertEquals(changeReceiver.getReceiverLogin(),"newTestLogin1");
    }

    @Test
    void findAllByReceiverLogin() {
        init();
        Assertions.assertEquals(commentRepository.findAllByReceiverLogin("testLogin1").getReceiverLogin(),"testLogin1");
    }

    @Test
    void deleteByReceiverLogin() {
        init();
        commentRepository.deleteByReceiverLogin("testLogin1");
        Receiver comment = commentRepository.findAllByReceiverLogin("testLogin1");
        Assertions.assertNull(comment);
    }

    @Test
    void updateComment() {
    }

    private void init(){
        Receiver receiver1 = new Receiver();
        Receiver receiver2 = new Receiver();
        Receiver receiver3 = new Receiver();

        receiver1.setId("testId1");
        receiver2.setId("testId2");
        receiver3.setId("testId3");

        receiver1.setReceiverLogin("testLogin1");
        receiver2.setReceiverLogin("testLogin2");
        receiver3.setReceiverLogin("testLogin3");

        commentRepository.save(receiver1);
        commentRepository.save(receiver2);
        commentRepository.save(receiver3);
    }
}