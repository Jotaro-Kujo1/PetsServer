package com.vmlebedev.petsbackend.repository;

import com.vmlebedev.petsbackend.models.Raiting;
import com.vmlebedev.petsbackend.models.UserForRaiting;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Set;

public interface RaitingCounterRepository extends CrudRepository<Raiting, String> {
    Raiting findAllByLogin(String login);
    @Transactional
    void deleteByLogin(String login);
}
