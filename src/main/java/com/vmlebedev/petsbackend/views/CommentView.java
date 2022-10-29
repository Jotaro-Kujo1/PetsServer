package com.vmlebedev.petsbackend.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentView {
    @JsonProperty("sender_login")
    private String senderLogin;
    private String profimg;
    private String text;
}
