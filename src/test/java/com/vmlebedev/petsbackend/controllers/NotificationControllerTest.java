package com.vmlebedev.petsbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmlebedev.petsbackend.models.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.vmlebedev.petsbackend.services.NotificationService;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RunWith(SpringRunner.class)
@WebMvcTest(NotificationController.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    @Test
    void createNotification() {
        Notification notification = new Notification("tmpId","tmpPic","tmpText","tmpSender","tmpReceiver","tmpDate");
        when(notificationService.saveNotification(notification)).thenReturn(notification);
        Notification newNotification = notificationService.saveNotification(notification);

        Assertions.assertEquals(notification.getText(),newNotification.getText());
    }

    @Test
    void getAll() throws Exception{
        when(notificationService.getAllNotifications("testLogin")).thenReturn(Arrays.asList(
                new Notification(),
                new Notification(),
                new Notification()
        ));
        mockMvc.perform(get("/notification/getNotifications?receiver_login=testLogin"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)));
    }



    private String asJsonString(final Object obj) {
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}