package com.example.demo.service;

import com.example.demo.model.Group;
import com.example.demo.model.GroupStudents;
import com.example.demo.repository.domain.GroupRepository;
import com.example.demo.service.validators.GroupValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Optional;

@Service
public class GroupService {
    private final Connection conn;

    private final GroupRepository groupRepository;
    private final GroupValidator validator;

    @Autowired
    public GroupService( GroupRepository groupRepository) throws Exception {
        this.conn= DriverManager.getConnection("jdbc:postgresql://localhost:5433/nitro",
                "postgres", "postgres");
        this.groupRepository = groupRepository;
        this.validator = new GroupValidator();
    }

    public Group addGroup( Group g) throws Exception {
        validator.validate(g);

        Optional<Group> group = groupRepository.getGroupByNameYear(conn,g.getName(),g.getYear().getValue());
        if (group.isPresent()) {
            throw new IllegalArgumentException("already exists");
        }

        return groupRepository.add(conn, g);
    }

    public GroupStudents getGroupStudentsById( Long id) throws Exception{
        Optional<GroupStudents> gs = groupRepository.getGroupStdeuntsById(conn, id);
        if (gs.isEmpty()) {
            throw new IllegalArgumentException("group not found");
        }
        return gs.get();
    }

}
