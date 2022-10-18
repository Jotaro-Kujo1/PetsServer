package com.vmlebedev.petsbackend.repository;

import com.vmlebedev.petsbackend.models.Notification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;


public interface NotificationRepository extends CrudRepository<Notification,String> {
    List<Notification> findAllByReceiverLogin(String login);

}
