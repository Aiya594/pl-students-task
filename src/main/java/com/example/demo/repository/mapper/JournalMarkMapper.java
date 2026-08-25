package com.example.demo.repository.mapper;

import com.example.demo.entity.JournalMarkEntity;
import com.example.demo.model.JournalMark;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JournalMarkMapper {
    public JournalMarkEntity toEntity(JournalMark jm){
        return JournalMarkEntity
                .builder()
                .jmID(jm.getJmID())
                .mark(jm.getMark())
                .date(jm.getDate())
                .journalID(jm.getJournalID())
                .studentID(jm.getStudentID())
                .build();
    }

    public JournalMark toDomain(JournalMarkEntity jm){
        return JournalMark
                .builder()
                .jmID(jm.getJmID())
                .mark(jm.getMark())
                .date(jm.getDate())
                .journalID(jm.getJournalID())
                .studentID(jm.getStudentID())
                .build();
    }
}
