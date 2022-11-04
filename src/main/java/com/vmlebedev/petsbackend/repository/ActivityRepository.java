package com.vmlebedev.petsbackend.repository;

import com.vmlebedev.petsbackend.models.Activity;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;

import javax.transaction.Transactional;


public interface ActivityRepository extends CrudRepository<Activity,String>{
    Activity findAllByLogin(String login);

    @Transactional
    @Modifying
    @Query(value = "update Activity a set a.commentActivity = :commentActivity where a.login = :login")
    void updateCommentActivity(int commentActivity, String login);

    @Transactional
    @Modifying
    @Query(value = "update Activity a set a.likeActivity = :likeActivity where a.login = :login")
    void updateLikeActivity(int likeActivity, String login);

    @Transactional
    @Modifying
    @Query(value = "update Activity a set a.postActivity = :postActivity where a.login = :login")
    void updatePostActivity(int postActivity, String login);
}
