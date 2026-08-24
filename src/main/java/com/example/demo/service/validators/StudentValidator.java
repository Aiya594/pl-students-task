package com.example.demo.service.validators;

import com.example.demo.model.Student;
import com.example.demo.util.exceptions.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class StudentValidator {

    public void validate(Student s) throws Exception{
//        if (s == null) {
//            return;
//        }

//        if(s.getGroupID()== null) {
//            throw new IllegalArgumentException("Group must not be empty");
//        }

        if(s.getEmail() == null || s.getEmail().isBlank()) {
            throw new ValidationException("Email must not be empty");
        }

        if(s.getUsername() == null || s.getUsername().isBlank()) {
            throw new ValidationException("Username must not be empty");
        }
    }
}
