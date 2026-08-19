package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.Year;

@Data
@AllArgsConstructor
@Builder
public class Group {
    private Long groupId;
    private String name;
    private Year year;

}
