package com.example.demo.controller;


import com.example.demo.model.JournalMark;
import com.example.demo.service.JournalMarkService;
import com.example.demo.service.JournalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/v1/journal/mark")
@AllArgsConstructor
public class JournalMarkController {

    private final JournalMarkService journalMarkService;

    @PostMapping
    public JournalMark add(@RequestBody JournalMark journalMark) {
        return journalMarkService.add(journalMark);
    }

    @PutMapping("/{id}")
    public JournalMark updateMark(
            @PathVariable Long id,
            @RequestParam int mark
    ) {
        return journalMarkService.updateMark(id, mark);
    }

    @GetMapping("/journal/{journalId}")
    public List<JournalMark> getMarks(
            @PathVariable Long journalId
    ) {
        return journalMarkService.getMarks(journalId);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return journalMarkService.delete(id);
    }
}