package com.example.demo.repository.mapper;


import com.example.demo.entity.SubjectEntity;
import com.example.demo.model.Subject;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SubjectMapper {
    public Subject toDomain(SubjectEntity sb) {
        return Subject.builder().subjectID(sb.getSubjectID()).name(sb.getName()).build();
    }

    public SubjectEntity toEntity(Subject sb) {
        return SubjectEntity.builder().subjectID(sb.getSubjectID()).name(sb.getName()).build();
    }
}
