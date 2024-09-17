package com.encode_initiative.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
public class ESRole {
    @Field(type = FieldType.Text)
    private String roleName;
}
