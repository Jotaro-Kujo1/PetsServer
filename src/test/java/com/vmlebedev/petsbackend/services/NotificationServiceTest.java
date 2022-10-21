package com.vmlebedev.petsbackend.services;

import com.vmlebedev.petsbackend.models.Notification;
import com.vmlebedev.petsbackend.repository.NotificationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@WebMvcTest(NotificationService.class)
class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;

    @MockBean
    private NotificationRepository notificationRepository;

    @Test
    void saveNotification() {
        Notification notification = new Notification("tmpId","tmpPic","tmpText","tmpSender","tmpReceiver","tmpDate");
        when(notificationRepository.save(notification)).thenReturn(notification);

        Notification savedNotification = notificationService.saveNotification(notification);
        Assertions.assertEquals(notification.getId(),savedNotification.getId());
    }

    @Test
    void getAllNotifications() {
        when(notificationRepository.findAllByReceiverLogin("tmpLogin")).thenReturn(Arrays.asList(
                new Notification(),
                new Notification(),
                new Notification()
        ));
        List<Notification> list = notificationRepository.findAllByReceiverLogin("tmpLogin");
        Assertions.assertEquals(list.size(),3);
    }

}