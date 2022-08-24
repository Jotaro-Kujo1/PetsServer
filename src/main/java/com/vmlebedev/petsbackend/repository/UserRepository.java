package com.vmlebedev.petsbackend.repository;

import com.vmlebedev.petsbackend.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {

}
