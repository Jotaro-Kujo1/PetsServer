package com.vmlebedev.petsbackend.postBuilder;

import com.vmlebedev.petsbackend.models.Post;

public class PostDirector {
    private final PostBuilder builder;

    public PostDirector(PostBuilder builder){
        super();
        this.builder = builder;
        if(this.builder==null) throw new IllegalArgumentException("Builder exception");
    }

    public Post createPost(byte [] arr, String address, String area,String description, String login, String handler, boolean state, String profimg){
        return builder
                .createId()
                .createDate()
                .createImg(arr)
                .createPlaceOfResidence(address,area)
                .createDataFromQuery(description,login,handler,state,profimg)
                .build();
    }

    public Post createSimplePost(byte[] img, String description, String address, String login, String handler, boolean state, String profimg){
        return builder
                .createImg(img)
                .createSimplifiedDataFromQuery(description,address,login,handler,state,profimg)
                .build();
    }
}
