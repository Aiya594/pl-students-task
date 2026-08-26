package com.example.demo.exception;

public class SubjectNotFound extends NotFoundException{
    public SubjectNotFound(Long id) {
        super("Subject with id: " + id + " not found");
    }
}
