package com.example.demo.repository.domain;

import com.example.demo.model.Group;
import com.example.demo.model.GroupStudents;

import java.sql.Connection;

public interface GroupRepository {
    Group add(Connection connection, Group g) throws Exception;

    Group getGroupByNameYear(Connection conn, String name, int year) throws Exception;

    GroupStudents getGroupStdeuntsById(Connection connection, Long id) throws Exception;
}
