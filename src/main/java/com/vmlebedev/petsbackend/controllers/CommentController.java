package com.vmlebedev.petsbackend.controllers;

import com.vmlebedev.petsbackend.convertors.CommentView2Comment;
import com.vmlebedev.petsbackend.convertors.ReceiverView2Receiver;
import com.vmlebedev.petsbackend.models.Comment;
import com.vmlebedev.petsbackend.models.Receiver;
import com.vmlebedev.petsbackend.services.CommentService;
import com.vmlebedev.petsbackend.views.ReceiverView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {

    private final CommentService commentService;
    private final ReceiverView2Receiver converter;

    @Autowired
    public CommentController(CommentService commentService,ReceiverView2Receiver converter){
        this.commentService = commentService;
        this.converter = converter;
    }

    @PostMapping(value = "/createComment")
    public ResponseEntity<Receiver> createComment(@RequestBody ReceiverView receiverView){
        Receiver receiver = converter.convert(receiverView);
        if(commentService.checkIfExist(receiver)){
            Receiver newReceiver = commentService.saveReceiver(receiver);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path(newReceiver.getId())
                    .buildAndExpand(newReceiver.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }else {
            commentService.updateComments(receiver);
            return ResponseEntity.status(201).build();
        }
    }

    @GetMapping(value = "/getComments")
    public ResponseEntity<Iterable<Comment>> getComments(@RequestParam String receiver_login){
        if(commentService.getAllCommentsForUser(receiver_login) != null){
            return ResponseEntity.ok(commentService.getAllCommentsForUser(receiver_login));
        }else return ResponseEntity.status(204).build();
    }

}
