package com.example.demo.exception;

public class GroupAlreadyExists extends AlreadyExistsException {
    public GroupAlreadyExists(String name, int year) {
        super("Group with name:" + name + " and year:" + year + " already exists");
    }
}
