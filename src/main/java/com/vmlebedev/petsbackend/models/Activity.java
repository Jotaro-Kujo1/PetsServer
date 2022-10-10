package com.vmlebedev.petsbackend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "activities", schema = "public")
public class Activity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @JsonProperty("id")
    private String id;
    @Column(name = "activity_name")
    @JsonProperty("activity_name")
    private String activityName;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private UserActivity userActivity;
    @JsonProperty("date")
    private String date;
    @JsonProperty("activityimg")
    private String activityimg;
}
