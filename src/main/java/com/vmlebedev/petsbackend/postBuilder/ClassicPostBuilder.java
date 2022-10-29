package com.vmlebedev.petsbackend.postBuilder;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.vmlebedev.petsbackend.models.Post;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ClassicPostBuilder implements PostBuilder {
    private String id;
    private byte[] img;
    private String description;
    private String address;
    private String login;
    private String handler;
    private String date;
    private String area;
    private boolean state;
    private String profimg;


    public ClassicPostBuilder(){
        super();
    }

    @Override
    public PostBuilder createId(){
        String uniqueKey = UUID.randomUUID().toString();
        this.id = uniqueKey;
        return this;
    }

    @Override
    public PostBuilder createDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        this.date = formatter.format(new Date());
        return this;
    }

    @Override
    public PostBuilder createPlaceOfResidence(String address, String area) {
        this.address = address;
        this.area = area;
        return this;
    }

    @Override
    public PostBuilder createImg(byte [] arr) {
        img = arr;
        return this;
    }

    @Override
    public PostBuilder createDataFromQuery(String description, String login, String handler, boolean state, String profimg) {
        this.description = description;
        this.login = login;
        this.handler = handler;
        this.state = state;
        this.profimg = profimg;
        return this;
    }

    @Override
    public PostBuilder createSimplifiedDataFromQuery(String description, String address, String login, String handler, boolean state, String profimg) {
        return null;
    }

    @Override
    public Post build() {
        return new Post(id,img,description,address,login,handler,date,area,state,profimg);
    }
}
