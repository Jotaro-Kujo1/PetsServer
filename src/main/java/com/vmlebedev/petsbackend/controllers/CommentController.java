package com.vmlebedev.petsbackend.controllers;

import com.vmlebedev.petsbackend.models.Comment;
import com.vmlebedev.petsbackend.models.Receiver;
import com.vmlebedev.petsbackend.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping(value = "/createComment")
    public ResponseEntity<Receiver> createComment(@RequestBody Receiver receiver){
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

}
