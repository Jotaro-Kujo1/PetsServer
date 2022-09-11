package com.vmlebedev.petsbackend.repository;

import com.vmlebedev.petsbackend.models.Conversation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConversationRepository extends CrudRepository<Conversation, String> {
    List<Conversation> findAllBySenderLogin(String login);
}
