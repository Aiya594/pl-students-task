package com.example.demo.repository.persistence;

import com.example.demo.model.Group;
import com.example.demo.model.GroupStudents;
import com.example.demo.repository.domain.GroupRepository;

import java.sql.Connection;

public class GroupJRepository implements GroupRepository {

    @Override
    public Group add(Connection connection, Group g) throws Exception {
        String sql = "INSERT INTO groups (name, year) VALUES (?,?)";

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
                WHERE s.group_id=?
                JOIN groups g ON s.group_id=g.group_id                
                """;

        return null;
    }
}
