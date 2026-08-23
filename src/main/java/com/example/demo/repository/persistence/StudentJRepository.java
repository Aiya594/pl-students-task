package com.example.demo.repository.persistence;

import com.example.demo.entity.StudentEntity;
import com.example.demo.model.Student;
import com.example.demo.repository.domain.StudentRepository;
import com.example.demo.repository.mapper.StudentMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentJRepository implements StudentRepository {

    @Override
    public Student add(Connection connection, Student st) throws Exception {
        String sql= """
                INSERT INTO students (iin, email,username,group_id)
                VALUES(?,?,?,?)
                """;
        StudentEntity en= StudentMapper.toEntity(st);

        try(PreparedStatement ps=connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1,en.getIin());
            ps.setString(2,en.getEmail());
            ps.setString(3, en.getUsername());
            ps.setLong(4,en.getGroupID());

            ps.executeUpdate();

            try(ResultSet res = ps.getGeneratedKeys()){
                if(res.next()){
                    return Student
                            .builder()
                            .id(res.getLong(1))
                            .iin(res.getString("iin"))
                            .email(res.getString("email"))
                            .username(res.getString("username"))
                            .build();
                }
            }
        }
        return null;
    }

    @Override
    public List<Student> getStudents(Connection connection) throws Exception {
        String sql= "SELECT student_id,iin, email,username,group_id FROM students";
        try (PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet res=ps.executeQuery()){
            List<Student> list = new ArrayList<>();
            while(res.next()){
                StudentEntity entity = StudentEntity
                        .builder()
                        .studentID(res.getLong(1))
                        .build();
                Student student = StudentMapper.toDomain(entity);
                list.add(student);
            }
            return list;
        }
    }

    @Override
    public Optional<Student> getStudentById(Connection connection, Long id) throws Exception {
        String sql= """
                SELECT student_id, iin, email, username, group_id
                FROM students WHERE student_id=?                
                """;
        try( PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1, id);
            try(ResultSet rs=ps.executeQuery()){
                if (rs.next()){
                    StudentEntity student = StudentEntity.builder()
                            .studentID(rs.getLong("student_id"))
                            .iin(rs.getString("iin"))
                            .email(rs.getString("email"))
                            .username(rs.getString("username"))
                            .groupID(rs.getLong("group_id"))
                            .build();
                    return Optional.of(StudentMapper.toDomain(student));
                }
                return Optional.empty();
            }
        }
    }

    @Override
    public Optional<Student> getStudentbByIIN(Connection conn, String iin) throws SQLException {
        String sql = "SELECT student_id, iin, email, username, group_id FROM students WHERE iin LIKE ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,iin);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    StudentEntity student = StudentEntity
                            .builder()
                            .studentID(rs.getLong("student_id"))
                            .username(rs.getString("username"))
                            .email(rs.getString("email"))
                            .groupID(rs.getLong("group_id"))
                            .iin(rs.getString("iin"))
                            .build();
                    return Optional.of(StudentMapper.toDomain(student));
                }
                return Optional.empty();
            }
        }
    }


    @Override
    public boolean delete(Connection connection, Long id) {
        String sql= "DELETE FROM students WHERE student_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
