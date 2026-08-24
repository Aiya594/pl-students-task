package com.example.demo.util.exceptions;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException(String mes){
        super(mes);
    }
}
