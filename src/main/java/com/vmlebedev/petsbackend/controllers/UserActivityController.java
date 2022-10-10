package com.vmlebedev.petsbackend.controllers;

import com.vmlebedev.petsbackend.models.Activity;
import com.vmlebedev.petsbackend.models.UserActivity;
import com.vmlebedev.petsbackend.services.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/activity")
@CrossOrigin
public class UserActivityController {

    private UserActivityService userActivityService;

    @Autowired
    public UserActivityController(UserActivityService userActivityService){
        this.userActivityService = userActivityService;
    }

    @PostMapping(value = "/createActivity")
    public ResponseEntity<UserActivity> createActivity(@RequestBody UserActivity userActivity){
        if(userActivityService.checkIfExist(userActivity)){
            UserActivity newUserActivity = userActivityService.saveUserActivity(userActivity);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path(newUserActivity.getId())
                    .buildAndExpand(newUserActivity.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }else {
            userActivityService.updateActivities(userActivity);
            return ResponseEntity.status(201).build();
        }
    }

    @GetMapping(value = "/getActivities")
    public ResponseEntity<Iterable<Activity>> getActivities(@RequestParam String login){
        if(userActivityService.getActivitiesForUser(login)!=null){
            return ResponseEntity.ok(userActivityService.getActivitiesForUser(login));
        }else return ResponseEntity.status(204).build();
    }
}
