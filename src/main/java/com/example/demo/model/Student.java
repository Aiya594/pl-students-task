package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Student {
    private Long id;
    private String iin;
    private String username;
    private String email;
    private Long groupID;
}
