package com.example.demo.util.exceptions;

public class SubjectAlreadyExists extends AlreadyExistsException{
    public SubjectAlreadyExists(String name) {
        super("Subject with name: " + name + " already exists");
    }
}
