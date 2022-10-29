package com.vmlebedev.petsbackend.postBuilder;

import com.vmlebedev.petsbackend.models.Post;

public class SimplifiedPostBuilder implements PostBuilder{
    private byte[] img;
    private String description;
    private String address;
    private String login;
    private String handler;
    private boolean state;
    private String profimg;

    public SimplifiedPostBuilder() {
        super();
    }

    @Override
    public PostBuilder createImg(byte [] arr) {
        img = arr;
        return this;
    }

    @Override
    public PostBuilder createSimplifiedDataFromQuery(String description,String address, String login, String handler, boolean state, String profimg) {
        this.description = description;
        this.address = address;
        this.login = login;
        this.handler = handler;
        this.state = state;
        this.profimg = profimg;
        return this;
    }

    @Override
    public PostBuilder createId() {
        return null;
    }

    @Override
    public PostBuilder createDate() {
        return null;
    }

    @Override
    public PostBuilder createPlaceOfResidence(String address, String area) {
        return null;
    }

    @Override
    public PostBuilder createDataFromQuery(String description, String login, String handler, boolean state, String profimg) {
        return null;
    }

    @Override
    public Post build() {
        return new Post("",img,description,address,login,handler,"","",state,profimg);
    }
}
