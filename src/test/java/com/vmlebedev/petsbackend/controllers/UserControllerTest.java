package com.vmlebedev.petsbackend.controllers;

import com.vmlebedev.petsbackend.models.User;
import com.vmlebedev.petsbackend.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    void getUsers() throws Exception{
        //Для того чтобы не юзать реальную БД используем некую заглушку. Когда вызывается метод findAll подставляются эти данные
        //благодаря механизму моков
        when(userService.findAll()).thenReturn(Arrays.asList(
                new User("test1","TestLogin1","TestPassword1"),
                new User("test2","TestLogin2","TestPassword2")
        ));
        //Иммитация работы контроллера
        mockMvc.perform(get("/forUsers/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[*].id",containsInAnyOrder("test1","test2")));
    }

    @Test
    void userCheck() {
    }

    @Test
    void createUser() {
    }

    @Test
    void deleteUser() {
    }
}