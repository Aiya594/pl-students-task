package com.example.demo.repository.mapper;

import com.example.demo.entity.StudentEntity;
import com.example.demo.model.Student;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StudentMapper {

    public Student toDomain(StudentEntity entity) {
        Student se=new Student(
                entity.getStudentID(),
                entity.getIin(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getGroupID());

        return se;
    }

    public StudentEntity toEntity(Student model) {
        StudentEntity se=new StudentEntity(
                model.getId(),
                model.getIin(),
                model.getUsername(),
                model.getEmail(),
                model.getGroupID());

        return se;
    }
}
