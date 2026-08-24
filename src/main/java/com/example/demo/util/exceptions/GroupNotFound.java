package com.example.demo.util.exceptions;

public class GroupNotFound extends NotFoundException{
    public GroupNotFound(Long id) {
        super("Group with id " + id + " not found");
    }
}
