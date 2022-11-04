package com.vmlebedev.petsbackend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="conversations", schema = "public")
@EntityListeners(AuditingEntityListener.class)
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
    @Column(name = "created_date")
    @CreatedDate
    @Timestamp
    private Date createdDate;
    @Column(name = "modified_date")
    @LastModifiedDate
    @Timestamp
    private Date modifiedDate;

    public Conversation(String id, String senderLogin, String receiverLogin, String profimg) {
        this.id = id;
        this.senderLogin = senderLogin;
        this.receiverLogin = receiverLogin;
        this.profimg = profimg;
    }
}
