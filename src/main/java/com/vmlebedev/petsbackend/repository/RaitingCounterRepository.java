package com.vmlebedev.petsbackend.repository;

import com.vmlebedev.petsbackend.models.Raiting;
import org.springframework.data.repository.CrudRepository;

public interface RaitingCounterRepository extends CrudRepository<Raiting, String> {
    Raiting findAllByLogin(String login);
}
