package com.example.demo.controller;


import com.example.demo.config.DBConnection;
import com.example.demo.model.Group;
import com.example.demo.model.GroupStudents;
import com.example.demo.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/v1/group")
@AllArgsConstructor
public class GroupController {

    private final GroupService groupService;
    @PostMapping
    public ResponseEntity<Group> add(@RequestBody Group group) throws Exception {
        return ResponseEntity.ok(groupService.addGroup(group));
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<GroupStudents> getGroupStudentsById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(
                groupService.getGroupStudentsById(id)
        );
    }
}
