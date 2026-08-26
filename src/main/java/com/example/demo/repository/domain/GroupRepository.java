package com.example.demo.repository.domain;

import com.example.demo.model.Group;
import com.example.demo.model.GroupStudents;
import com.example.demo.model.Student;

import java.sql.Connection;
import java.util.Optional;

public interface GroupRepository {
    Group add(Connection connection, Group g) throws Exception;

    Optional<Group> getGroupByNameYear(Connection conn, String name, int year) throws Exception;

    Optional<GroupStudents> getGroupStdeuntsById(Connection connection, Long id) throws Exception;

    Optional<Group> getGroupByID(Connection conn, Long id) throws Exception;

}
