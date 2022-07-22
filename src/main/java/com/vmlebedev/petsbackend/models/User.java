package com.vmlebedev.petsbackend.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Login")
    private String login;
    @Column(name = "Password")
    private String password;

}
