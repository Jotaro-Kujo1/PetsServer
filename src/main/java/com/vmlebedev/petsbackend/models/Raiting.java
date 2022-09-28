package com.vmlebedev.petsbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "raitings", schema = "public")
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
    @Transient
    @JsonProperty("raitingLogins")
    private Set<UserForRaiting> tmpLogins;
}
