package com.vmlebedev.petsbackend.controllers;

import com.vmlebedev.petsbackend.convertors.NotificationView2Notification;
import com.vmlebedev.petsbackend.models.Notification;
import com.vmlebedev.petsbackend.services.NotificationService;
import com.vmlebedev.petsbackend.views.NotificationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/notification")
@CrossOrigin
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationView2Notification converter;

    @Autowired
    public NotificationController(NotificationService notificationService,NotificationView2Notification converter){
        this.notificationService = notificationService;
        this.converter = converter;
    }

    @PostMapping("/createNotification")
    public ResponseEntity<Notification> createNotification(@RequestBody NotificationView notificationView){
        Notification notification = converter.convert(notificationView);
        Notification newNotification = notificationService.saveNotification(notification);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(newNotification.getId())
                .buildAndExpand(newNotification.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/getNotifications")
    public ResponseEntity<Iterable<Notification>> getAll(@RequestParam String receiver_login){
        return ResponseEntity.ok(notificationService.getAllNotifications(receiver_login));
    }

    @DeleteMapping("/deleteNotification")
    public ResponseEntity<?> deleteNotification(@RequestParam String id){
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getNotificationsAmount")
    public ResponseEntity<Integer> getNotificationsAmount(@RequestParam String receiver_login){
        return ResponseEntity.ok(notificationService.getNotificationsAmount(receiver_login));
    }
}
