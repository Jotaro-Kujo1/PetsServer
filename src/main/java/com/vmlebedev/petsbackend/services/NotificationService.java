package com.vmlebedev.petsbackend.services;

import com.vmlebedev.petsbackend.models.Notification;
import com.vmlebedev.petsbackend.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {

    private NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }

    public Notification saveNotification(Notification notification){
        String uniqueKey = UUID.randomUUID().toString();
        notification.setId(uniqueKey);
        return notificationRepository.save(notification);
    }

    public List<Notification> getAllNotifications(String login){
        return notificationRepository.findAllByReceiverLogin(login);
    }

    public void deleteNotification(String id){
        notificationRepository.deleteById(id);
    }
}
