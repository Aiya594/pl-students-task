package com.example.demo.exception;

public class GroupNotFound extends NotFoundException{
    public GroupNotFound(Long id) {
        super("Group with id " + id + " not found");
    }
}
