package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;


@Data
@AllArgsConstructor
@Builder
public class JournalMark {
    private Long jmID;

    private int mark;
    private Date date;

    private Long journalID;
    private Long studentID;
}
