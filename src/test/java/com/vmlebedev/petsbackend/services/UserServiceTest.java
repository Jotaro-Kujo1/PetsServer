package com.vmlebedev.petsbackend.services;

import com.vmlebedev.petsbackend.controllers.UserController;
import com.vmlebedev.petsbackend.models.User;
import com.vmlebedev.petsbackend.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@WebMvcTest(UserService.class)
class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void findById() {
        Optional<User> userMock = Optional.of(new User());
        userMock.get().setId("testId");
        when(userRepository.findById(userMock.get().getId())).thenReturn(Optional.of(userMock.get()));
        Optional<User> user = userRepository.findById(userMock.get().getId());
        Assert.assertEquals(user,userMock);
    }

    @Test
    void findAll() {
        List<User> usersFromMock = new ArrayList<User>();
        when(userRepository.findAll()).thenReturn(usersFromMock);
        List<User> users = userService.findAll();
        Assert.assertEquals(users,usersFromMock);
    }

    @Test
    void saveUser() {
        User userMock = new User("testId","testLogin","testPassword");
        when(userRepository.save(userMock)).thenReturn(userMock);
        User user = userRepository.save(userMock);
        Assert.assertEquals(user,userMock);
    }

}