package com.vmlebedev.petsbackend.repository;

import com.vmlebedev.petsbackend.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, String> {

}
