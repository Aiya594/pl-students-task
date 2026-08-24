package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Journal {
    private Long journalId;
    private String studyYear;

    private Long groupID;
    private Long subjectID;
    private Long studentID;
}
