package com.example.demo.service;

import com.example.demo.model.Group;
import com.example.demo.model.GroupStudents;
import com.example.demo.repository.domain.GroupRepository;
import com.example.demo.service.validators.GroupValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class GroupService {
    private final Connection conn;

    private final GroupRepository groupRepository;
    private final GroupValidator validator;

    @Autowired
    public GroupService( GroupRepository groupRepository, GroupValidator validator) throws Exception {
        this.conn= DriverManager.getConnection("jdbc:postgresql://localhost:5433/nitro",
                "postgres", "postgres");
        this.groupRepository = groupRepository;
        this.validator = validator;
    }

    public Group addGroup( Group g) throws Exception {
        validator.validate(g);

        Group group = groupRepository.getGroupByNameYear(conn,g.getName(),g.getYear().getValue());
        if(group.getName().equals(g.getName()) && group.getYear().equals(g.getYear())) {
            throw new IllegalArgumentException("Already exists");
        }

        return groupRepository.add(conn, g);
    }

    public GroupStudents getGroupStudentsById( Long id) throws Exception{
        return groupRepository.getGroupStdeuntsById(conn, id);
    }

}
