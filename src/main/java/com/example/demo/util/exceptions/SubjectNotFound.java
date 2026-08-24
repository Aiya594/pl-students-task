package com.example.demo.util.exceptions;

public class SubjectNotFound extends NotFoundException{
    public SubjectNotFound(Long id) {
        super("Subject with id: " + id + " not found");
    }
}
