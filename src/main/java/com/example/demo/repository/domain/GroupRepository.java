package com.example.demo.repository.domain;

import com.example.demo.model.Group;
import com.example.demo.model.GroupStudents;
import com.example.demo.model.Student;

import java.sql.Connection;

public interface GroupRepository {
    Group add(Connection connection, Group g) throws Exception;
//    boolean archive(Connection connection, Long groupId) throws Exception;
    //   GroupStudents getGroupById( Сonnection connection,Long id) throws Exception;

    GroupStudents getGroupById(Connection connection, Long id) throws Exception;
}
