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



    //пока что так потом исправлю
    // коннекшн передавать с сервиса
    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;

    }

    @PostMapping("/")
    public Group add(@RequestBody Group group) throws Exception {
        return groupService.addGroup(group);
    }

    @GetMapping("/{id}/students")
    public GroupStudents getGroupStudentsById(Long id) throws Exception {
        return groupService.getGroupStudentsById(id);
    }
}
