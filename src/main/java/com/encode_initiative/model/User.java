package com.encode_initiative.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Integer numberOfSearches;
    @OneToMany( mappedBy = "user")
    private List<Role> roleList;
}
