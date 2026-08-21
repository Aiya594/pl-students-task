package com.example.demo.repository.persistence;

import com.example.demo.entity.GroupEntity;
import com.example.demo.model.Group;
import com.example.demo.model.GroupStudents;
import com.example.demo.repository.domain.GroupRepository;
import com.example.demo.repository.mapper.GroupMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GroupJRepository implements GroupRepository {

    @Override
    public Group add(Connection connection, Group g) throws Exception {
        String sql = "INSERT INTO groups (name, year) VALUES (?,?)";

        try(PreparedStatement ps = connection.prepareStatement(sql,
                PreparedStatement.RETURN_GENERATED_KEYS)) {

            GroupEntity e = GroupMapper.toEntity(g);

            ps.setString(1, e.getName());
            ps.setInt(2, e.getYear());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {

                    return Group
                            .builder()
                            .groupId(rs.getLong(1))
                            .build();
                }
            }
        }



        return null;
    }

    @Override
    public GroupStudents getGroupById(Connection connection, Long id) throws Exception {
        String sql = """
            SELECT g.group_id, s.student_id
            FROM students s
            LEFT JOIN groups g ON s.group_id = g.group_id
            WHERE g.group_id = ?
            """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                List<Long> studentIds = new ArrayList<>();
                Long groupId = null;

                while (rs.next()) {
                    groupId = rs.getLong("group_id");
                    studentIds.add(rs.getLong("student_id"));
                }

                if (groupId == null) {
                    return null;
                }

                GroupStudents result = GroupStudents.builder().groupId(groupId).studentIds(studentIds).build();

                return result;
            }
        }
    }
//    @Override
//    public boolean archive(Connection connection, Long groupId) throws Exception {
//        return false;
//    }





}

