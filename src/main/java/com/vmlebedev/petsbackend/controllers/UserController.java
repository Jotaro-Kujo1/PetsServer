package com.vmlebedev.petsbackend.controllers;

import com.vmlebedev.petsbackend.models.User;
import com.vmlebedev.petsbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/forUsers")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<Iterable<User>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        Optional<User> user = Optional.ofNullable(userService.findById(id));
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/user", method = {RequestMethod.POST})
    public ResponseEntity<User> createUser(@RequestBody User user){
        User result = userService.saveUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(Integer.toString(user.getId()))
                .buildAndExpand(result.getLogin())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
//alt+o+l