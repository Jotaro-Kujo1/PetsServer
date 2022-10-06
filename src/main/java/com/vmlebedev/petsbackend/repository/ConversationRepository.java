package com.vmlebedev.petsbackend.repository;

import com.vmlebedev.petsbackend.models.Conversation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ConversationRepository extends CrudRepository<Conversation, String> {
    List<Conversation> findAllBySenderLogin(String login);
    @Transactional
    @Modifying
    @Query(value = "update Conversation u set u.profimg = :profimg where u.receiverLogin = :receiver_login")
    void updateConversation(@Param(value = "receiver_login")String login, @Param(value = "profimg")String profimg);
}
