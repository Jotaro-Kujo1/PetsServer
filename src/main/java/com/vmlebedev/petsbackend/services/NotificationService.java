package com.vmlebedev.petsbackend.services;

import com.vmlebedev.petsbackend.models.Notification;
import com.vmlebedev.petsbackend.repository.NotificationRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class NotificationService {

    private NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }

    public Notification saveNotification(Notification notification){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        notification.setDate(formatter.format(new Date()));
        return notificationRepository.save(notification);
    }

    public List<Notification> getAllNotifications(String login){
        List<Notification> list = notificationRepository.findAllByReceiverLogin(login);
        Collections.sort(list);
        return list;
    }

    public void deleteNotification(String id){
        notificationRepository.deleteById(id);
    }

    public int getNotificationsAmount(String login){
        return notificationRepository.findAllByReceiverLogin(login).size();
    }
}
