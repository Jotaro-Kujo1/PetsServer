package com.vmlebedev.petsbackend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="posts", schema = "public")
public class Post {
    @Id
    @JsonProperty("id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @JsonProperty("img")
    private byte[] img;
    @JsonProperty("description")
    private String description;
    @JsonProperty("address")
    private String address;
    @JsonProperty("login")
    private String login;
    @JsonProperty("handler")
    private String handler;
    @JsonProperty("date")
    private String date;
    @JsonProperty("area")
    private String area;
    @JsonProperty("state")
    private boolean state;
    @JsonProperty("profimg")
    private String profimg;

}
