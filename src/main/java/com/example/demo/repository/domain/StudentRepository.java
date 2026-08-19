package com.example.demo.repository.domain;


import com.example.demo.model.Student;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    Student add(Connection connection, Student st) throws Exception;
    List<Student> getStudents(Connection connection) throws Exception;
    Optional<Student> getStudentById(Connection connection, Long id) throws Exception;
//    Student getStudentbByUsername(Long id);
    boolean delete(Connection connection, Long id) throws Exception;
}
