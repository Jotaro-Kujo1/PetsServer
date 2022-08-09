package com.vmlebedev.petsbackend.controllers;

import com.vmlebedev.petsbackend.models.Post;
import com.vmlebedev.petsbackend.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }


    @GetMapping("/allPosts")
    public ResponseEntity<Iterable<Post>> getAllUPosts(){
        return ResponseEntity.ok(postService.findAll());
    }


    @RequestMapping(value = "/byteConverter", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> byteConverter(@RequestParam("file") MultipartFile multipartFile){
        try {
            byte[] arr = postService.toByteArrConverter(multipartFile);
            return ResponseEntity.ok(arr);
        }catch (IOException ex){
            ex.printStackTrace();
            return null;
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

    @DeleteMapping(value = "post/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable String id){
        postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
