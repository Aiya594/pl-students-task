package com.example.demo.controller;


import com.example.demo.config.DBConnection;
import com.example.demo.model.Group;
import com.example.demo.model.GroupStudents;
import com.example.demo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/v1/groups")
public class GroupController {

    private final GroupService groupService;
    private final Connection connection;


    //пока что так потом исправлю
    @Autowired
    public GroupController(GroupService groupService) throws SQLException {
        this.groupService = groupService;
        this.connection= DriverManager.getConnection("jdbc:postgresql://localhost:5433/nitro",
                "postgres", "postgres");
    }

    @PostMapping("/")
    public Group add(@RequestBody Group group) throws Exception {
        return groupService.addGroup(connection,group);
    }

    @GetMapping("/{id}")
    public GroupStudents getGroupById(Long id) throws Exception {
        return groupService.getGroupById(connection,id);
    }
}
