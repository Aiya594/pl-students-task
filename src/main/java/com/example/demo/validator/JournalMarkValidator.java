package com.example.demo.validator;

import com.example.demo.model.JournalMark;
import com.example.demo.exception.ValidationException;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

@UtilityClass
public class JournalMarkValidator {
    public void validate(JournalMark j)throws Exception{
        if (j.getJournalID()==null || j.getJournalID().toString().isBlank()){
            throw  new ValidationException("journal id must nor be empty");
        }



        if(j.getStudentID()==null || j.getStudentID().toString().isBlank()){
            throw  new ValidationException("student id must nor be empty");
        }
    }

    public void validateMark(int mark)throws Exception{
        if(mark<-2 || mark>100){
            throw new ValidationException("invalid mark");
        }
    }
}

