package com.example.demo.validator;

import com.example.demo.model.Journal;
import com.example.demo.exception.ValidationException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JournalValidator {

    public void validate(Journal j) throws Exception{
        if(j.getGroupID()==null || j.getGroupID().toString().isBlank()){
            throw  new ValidationException("group id must nor be empty");
        }

//        if(j.getStudentID()==null || j.getStudentID().toString().isBlank()){
//            throw  new ValidationException("student id must nor be empty");
//        }

        if(j.getStudyYear().isBlank()){
            throw new ValidationException("study year must not be empty");
        }

        if (!j.getStudyYear().matches("\\d{4}-\\d{4}")){
            throw new ValidationException("invalid study year(ex.:2024-2026");
        }

        if(j.getSubjectID()==null || j.getSubjectID().toString().isBlank()){
            throw new ValidationException("subject id must not be empty");
        }
    }
}
