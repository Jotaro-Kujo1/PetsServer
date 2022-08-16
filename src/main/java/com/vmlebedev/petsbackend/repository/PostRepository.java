package com.vmlebedev.petsbackend.repository;

import com.vmlebedev.petsbackend.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, String> {

}
