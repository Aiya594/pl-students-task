package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.domain.StudentRepository;
import com.example.demo.repository.persistence.StudentJRepository;
import com.example.demo.service.validators.StudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepo;
    private final Connection conn;
    private final StudentValidator validator;

    @Autowired
    public StudentService(StudentJRepository studentRepo) throws Exception {
        this.studentRepo = studentRepo;
        this.validator=new StudentValidator();
        this.conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/nitro",
                "postgres", "postgres");
    }

    public Student addStudent(Student student) throws Exception {
        validator.validate(student);

        Optional<Student> st = studentRepo.getStudentbByIIN(conn, student.getIin());
        if (st.isPresent()) {
            throw new Exception("Student already exists");
        }

        return studentRepo.add(conn,student);
    }

    public List<Student> getStudents() throws Exception {
        return studentRepo.getStudents(conn);
    }

    public Student getStudentById( Long id) throws Exception {
        Optional<Student> s = studentRepo.getStudentById(conn, id);

        if (s.isEmpty()) {
            throw new Exception("Student not found");
        }
        return s.get();
    }

}
