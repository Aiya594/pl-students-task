package com.example.demo.util.exceptions;

public class ValidationException extends RuntimeException{
    public ValidationException(String message){
        super( "Validation error: "+message);
    }
}
