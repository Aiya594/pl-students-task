package com.example.demo.service.validators;

import com.example.demo.model.Group;
import com.example.demo.util.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Year;

@Component
public class GroupValidator {

    public void validate(Group g) throws Exception{
        if (g.getName() == null || g.getName().isBlank()) {
            throw new ValidationException("Group name must not be empty");
        }

        if (g.getYear() == null) {
            throw new ValidationException("Group year must not be null");
        }

        if (g.getYear().isAfter(Year.now())) {
            throw new ValidationException("Group year cannot be in the future");
        }

    }
}
