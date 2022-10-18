package com.vmlebedev.petsbackend.controllers;

import com.vmlebedev.petsbackend.models.Activity;
import com.vmlebedev.petsbackend.models.Conversation;
import com.vmlebedev.petsbackend.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/activity")
@CrossOrigin
public class ActivityController {

    private ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService){
        this.activityService = activityService;
    }

    @PostMapping(value = "/updateLikes")
    public ResponseEntity<?> updateLikes(@RequestBody Activity activity){
        activityService.updateLikeActivity(activity.getLikeActivity(),activity.getLogin());
        return ResponseEntity.status(200).build();
    }

    @PostMapping(value = "/updateComments")
    public ResponseEntity<?> updateComments(@RequestBody Activity activity){
        activityService.updateCommentActivity(activity.getCommentActivity(),activity.getLogin());
        return ResponseEntity.status(200).build();
    }

    @PostMapping(value = "/updatePosts")
    public ResponseEntity<?> updatePosts(@RequestBody Activity activity){
        activityService.updatePostActivity(activity.getPostActivity(),activity.getLogin());
        return ResponseEntity.status(200).build();
    }

    @PostMapping(value = "/createActivity")
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity){
        if(activityService.checkIfExist(activity)){
            Activity newActivity = activityService.createActivity(activity);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path(newActivity.getId())
                    .buildAndExpand(newActivity.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }else return ResponseEntity.status(204).build();
    }

    @GetMapping(value = "/getActivity")
    public ResponseEntity<Activity> getActivity(@RequestParam String login){
        return ResponseEntity.ok(activityService.findAllByLogin(login));
    }

}
