package com.vmlebedev.petsbackend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "activities", schema = "public")
@EntityListeners(AuditingEntityListener.class)
public class Activity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @JsonProperty("id")
    private String id;
    @Column(name = "comment_activity")
    @JsonProperty("comment_activity")
    private int commentActivity;
    @Column(name = "like_activity")
    @JsonProperty("like_activity")
    private int likeActivity;
    @Column(name = "post_activity")
    @JsonProperty("post_activity")
    private int postActivity;
    @JsonProperty("login")
    private String login;


    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    @Timestamp
    private Date createdDate;
    @Column(name = "modified_date")
    @LastModifiedDate
    @Timestamp
    private Date modifiedDate;

    public Activity(String id, int commentActivity, int likeActivity, int postActivity, String login) {
        this.id = id;
        this.commentActivity = commentActivity;
        this.likeActivity = likeActivity;
        this.postActivity = postActivity;
        this.login = login;
    }
}
