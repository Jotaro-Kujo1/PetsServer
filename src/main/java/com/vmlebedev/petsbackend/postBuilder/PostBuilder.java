package com.vmlebedev.petsbackend.postBuilder;


import com.vmlebedev.petsbackend.models.Post;

public interface PostBuilder {
    PostBuilder createId();
    PostBuilder createDate();
    PostBuilder createPlaceOfResidence(String address, String area);
    PostBuilder createImg(byte [] arr);
    PostBuilder createDataFromQuery(String description, String login, String handler, boolean state, String profimg);
    Post build();
}
