package com.example.demo.repository.persistence;

import com.example.demo.config.DBConnection;
import com.example.demo.entity.StudentEntity;
import com.example.demo.model.Student;
import com.example.demo.repository.domain.StudentRepository;
import com.example.demo.repository.mapper.StudentMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentJRepository implements StudentRepository {
    private final DBConnection conn;

    public StudentJRepository(DBConnection conn) {
        this.conn = conn;
    }

    @Override
    public Student add(Student st) {
        String sql= """
                INSERT INTO students iin, email,username,group_id
                VALUES(?,?,?,?)
                RETURNING student_id
                """;
        StudentEntity en= StudentMapper.toEntity(st);

        try(Connection connection =conn.connect();
            PreparedStatement statement=connection.prepareStatement(sql)) {

            statement.setString(1,en.getIin());
            statement.setString(2,en.getEmail());
            statement.setString(3, en.getUsername());
            statement.setLong(4,en.getGroupID());

            statement.executeUpdate();

            try(ResultSet res = statement.getResultSet()){
                if(res.next()){
                    Long id = res.getLong("student_id");

                    return new Student(
                    id,
                    st.getIin(),
                    st.getUsername(),
                    st.getEmail(),
                    st.getGroupID());
                }
            } throw new SQLException("Student was not created");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Student> getStudents() {



        return List.of();
    }

    @Override
    public Student getStudentbById(Long id) {
        return null;
    }

    @Override
    public Student getStudentbByUsername(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
