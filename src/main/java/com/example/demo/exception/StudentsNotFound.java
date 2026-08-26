package com.example.demo.exception;

public class StudentsNotFound extends NotFoundException{
    public StudentsNotFound(Long id) {
        super("Student with id: " + id + " not found");
    }
}
