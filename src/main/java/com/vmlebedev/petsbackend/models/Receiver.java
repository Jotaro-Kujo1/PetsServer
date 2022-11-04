package com.vmlebedev.petsbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "receivers",schema = "public")
@EntityListeners(AuditingEntityListener.class)
public class Receiver {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @JsonProperty("id")
    private String id;
    @JsonProperty("receiver_login")
    @Column(name = "receiver_login")
    private String receiverLogin;
    @OneToMany(mappedBy = "receiver_login", cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private List<Comment> comments;
    @Column(name = "created_date")
    @CreatedDate
    @Timestamp
    private Date createdDate;
    @Column(name = "modified_date")
    @LastModifiedDate
    @Timestamp
    private Date modifiedDate;

    public Receiver(String id, String receiverLogin, List<Comment> comments) {
        this.id = id;
        this.receiverLogin = receiverLogin;
        this.comments = comments;
    }
}
