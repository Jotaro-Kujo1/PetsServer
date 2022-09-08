package com.vmlebedev.petsbackend.repository;

import com.vmlebedev.petsbackend.models.Conversation;
import org.springframework.data.repository.CrudRepository;

public interface ConversationRepository extends CrudRepository<Conversation, String> {

}
