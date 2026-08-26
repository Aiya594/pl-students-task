package com.example.demo.exception;

public class JournalNotFound extends NotFoundException{
    public JournalNotFound(Long id) {
        super("Journal with id="+id+" not found");
    }
}
