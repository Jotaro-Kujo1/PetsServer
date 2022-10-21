package com.vmlebedev.petsbackend.repository;

import com.vmlebedev.petsbackend.models.Notification;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class NotificationRepositoryTest {

    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    public void saveNotification(){
        Notification notification = new Notification();
        Assertions.assertNotNull(notificationRepository.save(notification));
    }

    @Test
    public void deleteNotification(){
        init();
        List<Notification> list = notificationRepository.findAllByReceiverLogin("tmpReceiver1");
        Notification notification = list.get(0);
        notificationRepository.deleteById(notification.getId());
        List<Notification> list2 = notificationRepository.findAllByReceiverLogin("tmpReceiver1");
        Assertions.assertEquals(list2.size(),0);
    }

    @Test
    void findAllByReceiverLogin() {
        init();
        List<Notification> list = notificationRepository.findAllByReceiverLogin("tmpReceiver1");
        Assertions.assertEquals(list.size(),1);
    }

    private void init() {
        Notification notification1 = new Notification("tmpId1","tmpPic1","tmpText1","tmpSender1","tmpReceiver1","tmpDate1");
        Notification notification2 = new Notification("tmpId2","tmpPic2","tmpText2","tmpSender2","tmpReceiver2","tmpDate2");
        Notification notification3 = new Notification("tmpId3","tmpPic3","tmpText3","tmpSender3","tmpReceiver3","tmpDate3");

        notificationRepository.save(notification1);
        notificationRepository.save(notification2);
        notificationRepository.save(notification3);
    }
}