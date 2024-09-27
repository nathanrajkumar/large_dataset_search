package com.encode_initiative.commonusecaselargedatasetsearch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("User")
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
//    // full text search, speicfiy certain patterns to search for the lastname
//    @Searchable
    private String lastName;
    // specify and search based on this fields
    @Indexed
    private String username;
    private String password;
    private String status;
    @OneToMany( mappedBy = "user")
    private List<Role> roleList;
}
