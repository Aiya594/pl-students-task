package com.example.demo.service;


import com.example.demo.model.Subject;
import com.example.demo.repository.domain.SubjectRepository;
import com.example.demo.util.DBUtil;
import com.example.demo.validator.SubjectValidator;
import com.example.demo.exception.SubjectAlreadyExists;
import com.example.demo.exception.SubjectNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public Subject add(Subject subject) throws Exception {

        try (Connection conn = DBUtil.getConnection()) {
            SubjectValidator.validate(subject);

            Optional<Subject> sb = subjectRepository.findByName(conn, subject.getName());
            if (sb.isPresent()) {
                throw new SubjectAlreadyExists(subject.getName());
            }

            return subjectRepository.add(conn, subject);

        }catch (Exception e){
            return null;
        }
    }

    public Subject findById(Long id) throws Exception {
        try (Connection conn = DBUtil.getConnection()) {
            Optional<Subject> sb = subjectRepository.findById(conn , id);
            if(sb.isEmpty()){
                throw new SubjectNotFound(id);
            }
            return sb.get();

        }catch (Exception e){
            return null;
        }
    }

    public Optional<Subject> findByName(String name) throws Exception {
        try (Connection conn = DBUtil.getConnection()) {
            return subjectRepository.findByName(conn , name);

        }catch (Exception e){
            return Optional.empty();
        }
    }

    public List<Subject> listSubjects() throws Exception {
        try (Connection conn = DBUtil.getConnection()) {
            return subjectRepository.listSubjects(conn);
        } catch (Exception e){
            return null;
        }
    }
}
