package com.example.demo.service.validators;

import com.example.demo.model.Group;
import org.springframework.stereotype.Component;

import java.time.Year;

@Component
public class GroupValidator {

    public void validate(Group g) throws Exception{
        if (g.getName() == null || g.getName().isBlank()) {
            throw new IllegalArgumentException("Group name must not be empty");
        }

        if (g.getYear() == null) {
            throw new IllegalArgumentException("Group year must not be null");
        }

        if (g.getYear().isAfter(Year.now())) {
            throw new IllegalArgumentException(
                    "Group year cannot be in the future"
            );
        }

    }
}
