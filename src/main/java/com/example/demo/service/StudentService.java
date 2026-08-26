package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.domain.StudentRepository;
import com.example.demo.repository.persistence.StudentJRepository;
import com.example.demo.util.DBUtil;
import com.example.demo.validator.StudentValidator;
import com.example.demo.exception.StudentAlreadyExists;
import com.example.demo.exception.StudentsNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepo;


    public Student addStudent(Student student) throws Exception {
        try (Connection conn = DBUtil.getConnection()) {
            StudentValidator.validate(student);

            Optional<Student> st = studentRepo.getStudentbByIIN(conn, student.getIin());
            if (st.isPresent()) {
                throw new StudentAlreadyExists(student.getIin());
            }

            return studentRepo.add(conn,student);

        }catch (Exception e){
            return null;
        }


    }

    public List<Student> getStudents() throws Exception {
        try (Connection conn = DBUtil.getConnection()) {

            return studentRepo.getStudents(conn);

        }catch (Exception e){
            return null;
        }

    }

    public Student getStudentById( Long id) throws Exception {
        try (Connection conn = DBUtil.getConnection()) {
            Optional<Student> s = studentRepo.getStudentById(conn, id);

            if (s.isEmpty()) {
                throw new StudentsNotFound(id);
            }
            return s.get();

        }catch (Exception e){
            return null;
        }

    }

    public boolean deleteStudentById(Long id) throws Exception {
        try (Connection conn = DBUtil.getConnection()) {

            if (!studentRepo.delete(conn,id)){
                throw new StudentsNotFound(id);
            }
            return true;
        }catch (Exception e){
            return false;
        }



    }
}
