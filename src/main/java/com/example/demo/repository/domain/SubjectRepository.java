package com.example.demo.repository.domain;

import com.example.demo.model.Subject;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface SubjectRepository {
    Optional<Subject> findByName(Connection conn, String name) throws Exception;
    Subject add(Connection conn,Subject subject) throws Exception;
    List<Subject> listSubjects(Connection conn) throws Exception;
    Optional<Subject> findById(Connection conn,Long id) throws Exception;
}
