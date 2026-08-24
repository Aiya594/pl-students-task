package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@Builder
public class JournalMark {
    private Long jmID;

    private int mark;
    private LocalDateTime date;

    private Long journalID;
    private Long studentID;
}
