package com.vmlebedev.petsbackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message {
    private String sender_name;
    private String receiver_name;
    private String message;
    private String timestamp;
    private String profimg;
}
