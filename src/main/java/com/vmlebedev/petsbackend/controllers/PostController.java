package com.vmlebedev.petsbackend.controllers;

import com.vmlebedev.petsbackend.models.Post;
import com.vmlebedev.petsbackend.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
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



    @RequestMapping(value = "/createPost", method = {RequestMethod.POST})
    public ResponseEntity<Post> createPost(@RequestBody Post post){
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

    @GetMapping(value = "/getAllPosts")
    public ResponseEntity<Iterable<Post>> getAllPosts(){
        return ResponseEntity.ok(postService.findAll());
    }

    @DeleteMapping(value = "post/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable String id){
        postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
