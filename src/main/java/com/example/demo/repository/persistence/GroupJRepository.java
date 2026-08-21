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

//    @Override
//    public boolean archive(Connection connection, Long groupId) throws Exception {
//        return false;
//    }

    @Override
    public GroupStudents getGroupById(Long id) throws Exception {
        String sql = """
                SELECT g.group_id, s.student_id from students s
                JOIN groups g ON s.group_id=g.group_id
                WHERE s.group_id=?                
                """;

        return null;
    }
}
