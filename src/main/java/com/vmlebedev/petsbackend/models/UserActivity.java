package com.vmlebedev.petsbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.messaging.handler.annotation.SendTo;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_activity", schema = "public")
public class UserActivity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @JsonProperty("id")
    private String id;
    @JsonProperty("login")
    private String login;
    @OneToMany(mappedBy = "userActivity", cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private List<Activity> activities;
    @Transient
    @JsonProperty("activities")
    private List<Activity> tmpActivities;
}
