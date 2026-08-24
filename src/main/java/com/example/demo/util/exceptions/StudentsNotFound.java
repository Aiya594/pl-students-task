package com.example.demo.util.exceptions;

public class StudentsNotFound extends NotFoundException{
    public StudentsNotFound(Long id) {
        super("Student with id: " + id + " not found");
    }
}
