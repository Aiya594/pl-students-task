package com.example.demo.exception;

public class StudentAlreadyExists extends AlreadyExistsException{
    public StudentAlreadyExists(String iin) {
        super("Student with iin: " + iin + " already exists");
    }
}
