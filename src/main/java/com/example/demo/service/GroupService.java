package com.example.demo.service;

import com.example.demo.model.Group;
import com.example.demo.model.GroupStudents;
import com.example.demo.repository.domain.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.time.Year;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group addGroup(Connection connection, Group g) throws Exception {

        if(g.getName() == null || g.getName().isEmpty()) {
            throw new Exception("INVALID GROUP NAME");
        }

        if(g.getYear() == null || g.getYear().getValue() > Year.now().getValue()) {
            throw new Exception("INVALID YEAR");
        }

        return groupRepository.add(connection, g);
    }

    public GroupStudents getGroupById(Connection conn, Long id) throws Exception{
        return groupRepository.getGroupById(conn, id);
    }

}
