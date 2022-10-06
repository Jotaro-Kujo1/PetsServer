package com.vmlebedev.petsbackend.repository;

import com.vmlebedev.petsbackend.models.Comment;
import com.vmlebedev.petsbackend.models.Receiver;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends CrudRepository<Receiver,String> {
    Receiver findAllByReceiverLogin(String login);
    @Transactional
    void deleteByReceiverLogin(String login);
    @Transactional
    @Modifying
    @Query(value = "update Comment u set u.profimg = :profimg where u.senderLogin = :sender_login")
    void updateComment(@Param(value = "sender_login")String login, @Param(value = "profimg")String profimg);
}
