package com.vmlebedev.petsbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.Timestamp;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "raitings", schema = "public")
@EntityListeners(AuditingEntityListener.class)
public class Raiting {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @JsonProperty("id")
    private String id;
    @JsonProperty("login")
    private String login;
    @OneToMany(mappedBy = "raiting", cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnore
    private Set<UserForRaiting> raitingLogins;
    @Column(name = "created_date")
    @CreatedDate
    @Timestamp
    private Date createdDate;
    @Column(name = "modified_date")
    @LastModifiedDate
    @Timestamp
    private Date modifiedDate;

    public Raiting(String id, String login, Set<UserForRaiting> raitingLogins) {
        this.id = id;
        this.login = login;
        this.raitingLogins = raitingLogins;
    }
}
