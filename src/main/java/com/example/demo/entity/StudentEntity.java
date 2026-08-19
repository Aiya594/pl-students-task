package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StudentEntity {
    private Long studentID;
    private String username;
    private String email;
    private Long groupID;
    private String iin;
}
