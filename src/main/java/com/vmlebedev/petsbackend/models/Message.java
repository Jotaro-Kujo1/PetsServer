package com.vmlebedev.petsbackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Message {
    private String sender_name;
    private String receiver_name;
    private String message;
    private String timestamp;
}
