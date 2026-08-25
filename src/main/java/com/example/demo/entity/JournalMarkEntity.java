package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class JournalMarkEntity {
    private Long jmID;

    private int mark;
    private Date date;

    private Long journalID;
    private Long studentID;
}
