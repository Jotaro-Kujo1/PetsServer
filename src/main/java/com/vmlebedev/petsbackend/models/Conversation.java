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
@Table(name="conversations", schema = "public")
public class Conversation {
    @Id
    @JsonProperty("id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @JsonProperty("sender_login")
    @Column(name = "sender_login")
    private String senderLogin;
    @JsonProperty("receiver_login")
    @Column(name = "receiver_login")
    private String receiverLogin;
    @JsonProperty("profimg")
    private String profimg;
}
