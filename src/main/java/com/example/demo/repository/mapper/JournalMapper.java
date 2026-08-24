package com.example.demo.repository.mapper;

import com.example.demo.entity.JournalEntity;
import com.example.demo.model.Journal;

public class JournalMapper {

    public Journal toDomain(JournalEntity j){
        return Journal
                .builder()
                .journalId(j.getJournalID())
                .studyYear(j.getStudyYear())
                .groupID(j.getGroupID())
                .subjectID(j.getSubjectID())
                .studentID(j.getStudentID())
                .build();
    }

    public JournalEntity toEntity(Journal j){
        return JournalEntity
                .builder()
                .journalID(j.getJournalId())
                .studyYear(j.getStudyYear())
                .groupID(j.getGroupID())
                .subjectID(j.getSubjectID())
                .studentID(j.getStudentID())
                .build();
    }
}
