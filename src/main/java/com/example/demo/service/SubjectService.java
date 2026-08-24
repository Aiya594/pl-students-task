package com.example.demo.service;


import com.example.demo.model.Subject;
import com.example.demo.repository.domain.SubjectRepository;
import com.example.demo.service.validators.SubjectValidator;
import com.example.demo.util.exceptions.SubjectAlreadyExists;
import com.example.demo.util.exceptions.SubjectNotFound;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectValidator subjectValidator;
    private final Connection conn;

    public SubjectService(SubjectRepository subjectRepository) throws SQLException {
        this.subjectRepository = subjectRepository;
        this.subjectValidator = new SubjectValidator();
        this.conn= DriverManager.getConnection("jdbc:postgresql://localhost:5433/nitro",
                "postgres", "postgres");
    }

    public Subject add(Subject subject) throws Exception {
        subjectValidator.validate(subject);

        Optional<Subject> sb = subjectRepository.findByName(conn, subject.getName());
        if (sb.isPresent()) {
            throw new SubjectAlreadyExists(subject.getName());
        }

        return subjectRepository.add(conn, subject);
    }

    public Subject findById(Long id) throws Exception {
        Optional<Subject> sb = subjectRepository.findById(conn , id);
        if(!sb.isPresent()){
            throw new SubjectNotFound(id);
        }
        return sb.get();
    }

    public Optional<Subject> findByName(String name) throws Exception {
        return subjectRepository.findByName(conn , name);
    }

    public List<Subject> listSubjects() throws Exception {
        return subjectRepository.listSubjects(conn);
    }

}
