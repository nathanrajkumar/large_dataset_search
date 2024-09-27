package com.encode_initiative.commonusecaselargedatasetsearch.model;

import com.encode_initiative.commonusecaselargedatasetsearch.repository.SearchUserRepository;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;
    @ManyToOne
    private User user ;
}
