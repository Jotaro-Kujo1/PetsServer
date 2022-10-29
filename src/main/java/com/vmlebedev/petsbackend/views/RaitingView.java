package com.vmlebedev.petsbackend.views;

import com.vmlebedev.petsbackend.models.UserForRaiting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RaitingView {
    private String login;
    private Set<UserForRaitingView> raitingLogins;
}
