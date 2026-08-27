package com.example.demo.service;

import com.example.demo.exception.AlreadyExistsException;
import com.example.demo.exception.AppException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Student;
import com.example.demo.repository.domain.StudentRepository;
import com.example.demo.util.DBUtil;
import com.example.demo.validator.StudentValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepo;


    public Student addStudent(Student student)  {
        try (Connection conn = DBUtil.getConnection()) {
            StudentValidator.validate(student);

            Optional<Student> st = studentRepo.getStudentbByIIN(conn, student.getIin());
            if (st.isPresent()) {
                throw new AlreadyExistsException( "Student with iin "+ student.getIin() +" already exists");
            }

            return studentRepo.add(conn,student);

        }catch (Exception e){
            throw new AppException("Error: " + e);
        }


    }

    public List<Student> getStudents()  {
        try (Connection conn = DBUtil.getConnection()) {

            return studentRepo.getStudents(conn);

        }catch (Exception e){
            throw new AppException("Error: " + e);
        }

    }

    public Student getStudentById( Long id)  {
        try (Connection conn = DBUtil.getConnection()) {
            Optional<Student> s = studentRepo.getStudentById(conn, id);

            if (s.isEmpty()) {
                throw new NotFoundException("Student  with id="+ id +" not found");
            }
            return s.get();

        }catch (Exception e){
            throw new AppException("Error: " + e);
        }

    }

    public boolean deleteStudentById(Long id)  {
        try (Connection conn = DBUtil.getConnection()) {

            if (!studentRepo.delete(conn,id)){
                throw new NotFoundException("Student  with id="+ id +" not found");
            }
            return true;
        }catch (Exception e){
            throw new AppException("Error: " + e);
        }
    }
}
