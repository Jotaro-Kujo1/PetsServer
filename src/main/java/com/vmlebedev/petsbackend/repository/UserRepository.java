package com.vmlebedev.petsbackend.repository;

import com.vmlebedev.petsbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
