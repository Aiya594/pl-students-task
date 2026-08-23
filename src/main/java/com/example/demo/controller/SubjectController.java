package com.example.demo.controller;


import com.example.demo.model.Subject;
import com.example.demo.service.SubjectService;
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
    public List<Subject> listSubjects() throws Exception {
     return subjectService.listSubjects();
    }

    @PostMapping
    public Subject createSubject(@RequestBody Subject subject) throws Exception {
        return subjectService.add(subject);
    }

    @GetMapping("/{id}")
    public Subject getSubject(@PathVariable Long id) throws Exception {
        return subjectService.findById(id);
    }
}
