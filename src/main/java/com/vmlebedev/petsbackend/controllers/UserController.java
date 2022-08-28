package com.vmlebedev.petsbackend.controllers;

import com.vmlebedev.petsbackend.models.User;
import com.vmlebedev.petsbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/forUsers")
@CrossOrigin
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



    //Sign in
    @RequestMapping(value = "/user/checkUser", method = {RequestMethod.POST})
    public ResponseEntity<?> userCheck(@RequestBody User user){
        User result = userService.findByLogin(user);
        if(result!=null){
            return ResponseEntity.ok(user.getLogin());
        }else {
            return ResponseEntity.notFound().build();
        }
    }



    @RequestMapping(value = "/user", method = {RequestMethod.POST})
    public ResponseEntity<User> createUser(@RequestBody User user){
        if(userService.checkLogin(user)==null){
            User result = userService.saveUser(user);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path(user.getId())
                    .buildAndExpand(result.getLogin())
                    .toUri();
            return ResponseEntity.created(location).build();
        }else {
            return ResponseEntity.status(404).build();
        }

    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable String id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
//alt+o+l