package com.example.demo.repository.persistence;


import com.example.demo.entity.SubjectEntity;
import com.example.demo.model.Subject;
import com.example.demo.repository.domain.SubjectRepository;
import com.example.demo.repository.mapper.SubjectMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SubjectJRepository implements SubjectRepository {

    @Override
    public Optional<Subject> findByName(Connection conn, String name) throws Exception{
        String sql = """
                SELECT subject_id, name
                FROM subjects
                WHERE name = ?
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    SubjectEntity entity = SubjectEntity.builder()
                            .subjectID(rs.getLong("subject_id"))
                            .name(rs.getString("name"))
                            .build();

                    return Optional.of(SubjectMapper.toDomain(entity));
                }

                return Optional.empty();
            }

        }
    }

    @Override
    public Subject add(Connection conn, Subject subject) throws Exception {
        String sql = """
                INSERT INTO subjects (name)
                VALUES (?)
                """;
        try (PreparedStatement ps = conn.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS
        )) {
            ps.setString(1, subject.getName());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    SubjectEntity entity = SubjectEntity.builder()
                            .subjectID(id)
                            .name(subject.getName())
                            .build();
                    return SubjectMapper.toDomain(entity);
                }
            }
        }
        return null;
    }

    @Override
    public List<Subject> listSubjects(Connection conn) throws Exception {
        String sql = """
                SELECT subject_id, name
                FROM subjects
                ORDER BY subject_id
                """;

        List<Subject> subjects = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                SubjectEntity entity = SubjectEntity.builder()
                        .subjectID(rs.getLong("subject_id"))
                        .name(rs.getString("name"))
                        .build();
                subjects.add(SubjectMapper.toDomain(entity));
            }
            return subjects;
        }
    }

    @Override
    public Optional<Subject> findById(Connection conn, Long id) throws Exception {
        String sql = """
                SELECT subject_id, name
                FROM subjects
                WHERE subject_id = ?
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    SubjectEntity entity = SubjectEntity.builder()
                            .subjectID(rs.getLong("subject_id"))
                            .name(rs.getString("name"))
                            .build();

                    return Optional.of(SubjectMapper.toDomain(entity));
                }
                return Optional.empty();
            }
        }
    }
}
