package com.encode_initiative.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
// Elastic Search "table"
@Document(indexName = "users")
public class ESUser {
    @Id
    private Long id;
    @Field(type = FieldType.Text, name = "username")
    private String userName;
    @Field(type = FieldType.Text, name = "password")
    private String password;
    @Field(type = FieldType.Integer, name = "year")
    private Integer numberOfSearches;
    @Field(type = FieldType.Nested, name = "roles")
    private List<ESRole> roleList;
}
