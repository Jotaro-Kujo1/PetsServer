package com.vmlebedev.petsbackend.repository;

import com.vmlebedev.petsbackend.models.Raiting;
import com.vmlebedev.petsbackend.models.UserForRaiting;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserForRaitingRepository extends CrudRepository<UserForRaiting,String> {
    List<UserForRaiting> findAllByRaiting(Raiting raiting);
}
