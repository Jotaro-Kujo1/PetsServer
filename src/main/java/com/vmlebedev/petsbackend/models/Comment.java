package com.vmlebedev.petsbackend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "comments",schema = "public")
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @JsonProperty("id")
    private String id;
    @JsonProperty("sender_login")
    @Column(name = "sender_login")
    private String senderLogin;
    @JsonProperty("profimg")
    private String profimg;
    @JsonProperty("text")
    private String text;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Receiver receiver_login;
    @JsonProperty("date")
    private String date;
    @Column(name = "created_date")
    @CreatedDate
    @Timestamp
    private Date createdDate;
    @Column(name = "modified_date")
    @LastModifiedDate
    @Timestamp
    private Date modifiedDate;

    public Comment(String id, String senderLogin, String profimg, String text, Receiver receiver_login, String date) {
        this.id = id;
        this.senderLogin = senderLogin;
        this.profimg = profimg;
        this.text = text;
        this.receiver_login = receiver_login;
        this.date = date;
    }
}
