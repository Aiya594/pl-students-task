package com.example.demo.handler;


import com.example.demo.exception.AlreadyExistsException;
import com.example.demo.exception.AppException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.payload.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.rmi.AlreadyBoundException;

@ControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException exception) {
        ErrorResponse error = ErrorResponse
                                .builder()
                                .status(404)
                                .message(exception.getMessage())
                                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExists(AlreadyExistsException exc){
        ErrorResponse er = ErrorResponse
                .builder().status(409).message(exc.getMessage()).build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(er);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidation(ValidationException exc){
        ErrorResponse er = ErrorResponse
                .builder().status(400).message(exc.getMessage()).build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(er);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleAppException(AppException exc){
        ErrorResponse er = ErrorResponse
                .builder().status(500).message(exc.getMessage()).build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(er);
    }

}
