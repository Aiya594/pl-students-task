package com.example.demo.controller;

import com.example.demo.model.Journal;
import com.example.demo.service.JournalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/v1/journal")
@AllArgsConstructor
public class JournalController {

    private final JournalService journalService;

    @PostMapping
    public ResponseEntity<Journal> add(@RequestBody Journal j){
        return ResponseEntity.ok(journalService.addJournal(j));
    }

    @GetMapping
    public ResponseEntity<List<Journal>> list(){
        return ResponseEntity.ok(journalService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Journal> getById(@PathVariable Long id) {
        return ResponseEntity.ok(journalService.getById(id));
    }
}
