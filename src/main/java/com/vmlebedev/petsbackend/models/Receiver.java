package com.vmlebedev.petsbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "receivers",schema = "public")
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
    //@Transient
    //@JsonProperty("comments")
    //private List<Comment> tmpComments;
}
