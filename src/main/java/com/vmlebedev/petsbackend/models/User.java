package com.vmlebedev.petsbackend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users", schema = "public")
public class User {
    @NotNull
    @Id
    @JsonProperty("id")
    private int id;
    @JsonProperty("login")
    private String login;
    @JsonProperty("password")
    private String password;
}
