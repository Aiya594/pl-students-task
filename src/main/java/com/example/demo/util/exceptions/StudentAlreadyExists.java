package com.example.demo.util.exceptions;

public class StudentAlreadyExists extends AlreadyExistsException{
    public StudentAlreadyExists(String iin) {
        super("Student with iin: " + iin + " already exists");
    }
}
