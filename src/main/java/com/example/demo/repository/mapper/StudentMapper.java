package com.example.demo.repository.mapper;

import com.example.demo.entity.StudentEntity;
import com.example.demo.model.Student;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StudentMapper {

    public Student toDomain(StudentEntity entity) {
        Student se= Student
                .builder()
                .id(entity.getStudentID())
                .iin(entity.getIin())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .groupID(entity.getGroupID())
                .build();

        return se;
    }

    public StudentEntity toEntity(Student model) {
        StudentEntity se= StudentEntity
                .builder()
                .studentID(model.getId())
                .username(model.getUsername())
                .email(model.getEmail())
                .groupID(model.getGroupID())
                .iin(model.getIin())
                .build();

        return se;
    }
}
