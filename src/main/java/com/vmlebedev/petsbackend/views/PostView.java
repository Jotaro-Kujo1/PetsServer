package com.vmlebedev.petsbackend.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostView {
    private byte[] img;
    private String description;
    private String address;
    private String login;
    private String handler;
    private boolean state;
    private String profimg;
}
