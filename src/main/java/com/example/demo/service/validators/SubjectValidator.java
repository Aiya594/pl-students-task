package com.example.demo.service.validators;

import com.example.demo.model.Subject;
import com.example.demo.util.exceptions.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class SubjectValidator {
    public void validate(Subject sb) throws Exception {

        if (sb.getName().isBlank()) {
            throw new ValidationException("Subject name must not be blank");
        }

        if(sb.getName().length() < 3) {
            throw new ValidationException("Subject must have at least 3 characters");
        }
    }
}
