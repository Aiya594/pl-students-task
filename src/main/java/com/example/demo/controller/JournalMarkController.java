package com.example.demo.controller;


import com.example.demo.service.JournalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/v1/journal/mark")
@AllArgsConstructor
public class JournalMarkController {

    private final JournalService journalMarkService;

}
