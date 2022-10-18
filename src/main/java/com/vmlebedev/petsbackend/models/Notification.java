package com.vmlebedev.petsbackend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "notifications", schema = "public")
public class Notification {
    @Id
    @JsonProperty("id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @JsonProperty("profimg")
    private String profimg;
    @JsonProperty("text")
    private String text;
    @JsonProperty("sender_login")
    @Column(name = "sender_login")
    private String senderLogin;
    @JsonProperty("receiver_login")
    @Column(name = "receiver_login")
    private String receiverLogin;
}
