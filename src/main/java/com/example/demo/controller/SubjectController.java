package com.example.demo.controller;


import com.example.demo.model.Subject;
import com.example.demo.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/v1/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<List<Subject>> listSubjects() throws Exception {
     return ResponseEntity.ok(subjectService.listSubjects());
    }

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) throws Exception {
        return ResponseEntity.ok(subjectService.add(subject));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubject(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(subjectService.findById(id));
    }
}
