package com.vmlebedev.petsbackend.repository;

import com.vmlebedev.petsbackend.models.UserActivity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface UserActivityRepository extends CrudRepository<UserActivity,String> {
    UserActivity findAllByLogin(String login);
    @Transactional
    void deleteByLogin(String login);
}
