package com.vmlebedev.petsbackend.repository;

import com.vmlebedev.petsbackend.models.Post;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PostRepository extends CrudRepository<Post, String> {
    List<Post> findAllByArea(String area);
    List<Post> findAllByState(boolean state);
    List<Post> findAllByStateAndArea(boolean state, String area);
    List<Post> findAllByLogin(String login);
    @Transactional
    @Modifying
    @Query(value = "update Post u set u.profimg = :profimg where u.login = :login")
    void updatePosts(@Param(value="login") String login, @Param(value="profimg") String profimg);
}
