package com.example.demo.service;


import com.example.demo.exception.AlreadyExistsException;
import com.example.demo.exception.AppException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.Subject;
import com.example.demo.repository.domain.SubjectRepository;
import com.example.demo.util.DBUtil;
import com.example.demo.validator.SubjectValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public Subject add(Subject subject) {

        try (Connection conn = DBUtil.getConnection()) {
            SubjectValidator.validate(subject);

            Optional<Subject> sb = subjectRepository.findByName(conn, subject.getName());
            if (sb.isPresent()) {
                throw new AlreadyExistsException("Subject "+subject.getName()+"Already exists");
            }

            return subjectRepository.add(conn, subject);

        } catch (ValidationException | NotFoundException | AlreadyExistsException e) {
            throw e;
        } catch (Exception e){
            throw new AppException("Error: " + e);
        }
    }

    public Subject findById(Long id) {
        try (Connection conn = DBUtil.getConnection()) {
            Optional<Subject> sb = subjectRepository.findById(conn , id);
            if(sb.isEmpty()){
                throw new NotFoundException("Subject with id="+ id + "nt found");
            }
            return sb.get();

        } catch (ValidationException | NotFoundException | AlreadyExistsException e) {
            throw e;
        } catch (Exception e){
            throw new AppException("Error: " + e);
        }
    }

    public Optional<Subject> findByName(String name) {
        try (Connection conn = DBUtil.getConnection()) {
            return subjectRepository.findByName(conn , name);

        } catch (ValidationException | NotFoundException | AlreadyExistsException e) {
            throw e;
        } catch (Exception e){
            throw new AppException("Error: " + e);
        }
    }

    public List<Subject> listSubjects() {
        try (Connection conn = DBUtil.getConnection()) {
            return subjectRepository.listSubjects(conn);
        } catch (ValidationException | NotFoundException | AlreadyExistsException e) {
            throw e;
        } catch (Exception e){
            throw new AppException("Error: " + e);
        }
    }
}
