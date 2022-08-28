package com.vmlebedev.petsbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmlebedev.petsbackend.models.User;
import com.vmlebedev.petsbackend.services.UserService;
import net.minidev.json.JSONArray;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    //Для подмены
    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper mapper;

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
    void userCheck() throws Exception{
        User user = new User();
        user.setLogin("TestLogin");
        //Новый пользователь т.к. метод возвращает пользователя
        Mockito.doReturn(new User())
                .when(userService)
                .findByLogin(user);
        Assert.assertNotNull(user.getLogin());
    }


    @Test
    void createUser() throws Exception{

    }

    private String asJsonString(final Object obj) {
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Test
    void deleteUser() {
    }
}