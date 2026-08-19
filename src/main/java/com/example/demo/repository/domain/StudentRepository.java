package com.example.demo.repository.domain;


import com.example.demo.model.Student;

import java.util.List;

public interface StudentRepository {
    Student add(Student st);
    List<Student> getStudents();
    Student getStudentbById(Long id);
    Student getStudentbByUsername(Long id);
    void delete(Long id);
}
