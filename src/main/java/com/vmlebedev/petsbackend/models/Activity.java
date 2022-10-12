package com.vmlebedev.petsbackend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;





@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "activities", schema = "public")
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
}
