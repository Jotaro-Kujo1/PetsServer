package com.vmlebedev.petsbackend.controllers;

import com.vmlebedev.petsbackend.convertors.PostView2Post;
import com.vmlebedev.petsbackend.models.Post;
import com.vmlebedev.petsbackend.services.CommentService;
import com.vmlebedev.petsbackend.services.ConversationService;
import com.vmlebedev.petsbackend.services.PostService;
import com.vmlebedev.petsbackend.views.PostView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {
    private final PostService postService;
    private final ConversationService conversationService;

    private final CommentService commentService;
    private final PostView2Post converter;

    @Autowired
    public PostController(PostService postService,ConversationService conversationService,CommentService commentService,PostView2Post converter){
        this.postService = postService;
        this.conversationService = conversationService;
        this.commentService = commentService;
        this.converter = converter;
    }


    @RequestMapping(value = "/byteConverter", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> byteConverter(@RequestParam("file") MultipartFile multipartFile){
        byte[] arr =postService.toByteArrConverter(multipartFile);

        if(arr!=null){
            return ResponseEntity.ok(arr);
        }else{
            return ResponseEntity.status(404).build();
        }
    }



    @PostMapping(value = "/createPost")
    public ResponseEntity<Post> createPost(@RequestBody PostView postView){
        Post post = converter.convert(postView);
        if(postService.checkPost(post)){
            Post newPost = postService.savePost(post);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path(newPost.getId())
                    .buildAndExpand(newPost.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }else {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping(value = "/getAllPosts/lost")
    public ResponseEntity<Iterable<Post>> getAllLostPosts(){
        return ResponseEntity.ok(postService.findAllByState(true));
    }

    @GetMapping(value = "/getAllPosts/searched")
    public ResponseEntity<Iterable<Post>> getAllSearchedPosts(){
        return ResponseEntity.ok(postService.findAllByState(false));
    }

    @DeleteMapping(value = "/post/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable String id){
        postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/getLostForArea/{area}")
    public ResponseEntity<Iterable<Post>> getAllLostForArea(@PathVariable String area){
        return ResponseEntity.ok(postService.findAllByStateAndArea(true,area));
    }

    @GetMapping(value = "/getSearchedForArea/{area}")
    public ResponseEntity<Iterable<Post>> getAllSearchedForArea(@PathVariable String area){
        return ResponseEntity.ok(postService.findAllByStateAndArea(false,area));
    }

    @GetMapping(value = "/getAllUsersPosts/{login}")
    public ResponseEntity<Iterable<Post>> getAllUsersPosts(@PathVariable String login){
        return ResponseEntity.ok(postService.findAllUsersPosts(login));
    }

    @GetMapping(value = "/getAllPosts")
    public ResponseEntity<Iterable<Post>> getAllPosts(){
        return ResponseEntity.ok(postService.findAll());
    }

    @PostMapping(value = "/updatePicture")
    public ResponseEntity<?> updatePicture(@RequestParam String login, @RequestParam String profimg){
        postService.updatePictureAllUsersPosts(login,profimg);
        conversationService.updatePictureAllConversations(login,profimg);
        commentService.updatePictureAllConversations(login,profimg);
        return ResponseEntity.ok().build();
    }
}
