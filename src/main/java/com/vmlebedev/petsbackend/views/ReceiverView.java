package com.vmlebedev.petsbackend.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vmlebedev.petsbackend.models.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReceiverView {
    @JsonProperty("receiver_login")
    private String receiverLogin;
    private List<CommentView> comments;
}
