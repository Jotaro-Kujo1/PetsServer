package com.vmlebedev.petsbackend.repository;

import com.vmlebedev.petsbackend.models.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public String saveUser(){
        User user = new User();
        user.setLogin("testLogin");
        user.setPassword("testPassword");
        userRepository.save(user);
        Assertions.assertNotNull(user.getId());
        return user.getId();
    }

    @Test
    public void getUser(){
        String id = saveUser();
        Optional<User> user = userRepository.findById(id);
        Assertions.assertEquals(user.get().getId(),id);
    }

    @Test
    public void getUsers(){
        init();
        List<User> users = (List<User>) userRepository.findAll();
        Assertions.assertEquals(users.size(),3);
    }

    @Test
    public void updateUser(){
        String id = saveUser();
        Optional<User> user = userRepository.findById(id);
        user.get().setLogin("newTestLogin");
        User changeUser = userRepository.save(user.get());
        Assertions.assertEquals(changeUser.getLogin(),"newTestLogin");
    }

    @Test
    public void deleteUser(){
        User tmp = new User();
        String id = saveUser();
        userRepository.deleteById(id);
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) tmp = null;
        Assertions.assertNull(tmp);
    }

    private void init(){
        userRepository.save(new User("test1","testLogin1","testPassword1"));
        userRepository.save(new User("test2","testLogin2","testPassword2"));
        userRepository.save(new User("test3","testLogin3","testPassword3"));
    }

}