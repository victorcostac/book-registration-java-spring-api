package com.example.bookregistration.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.bookregistration.exception.RecordNotFoundException;

@RestControllerAdvice
public class AplicationControllerAdvice {
    @ExceptionHandler(RecordNotFoundException.class)
    public String handleNotFoundException(RecordNotFoundException ex) {
        return ex.getMessage();
    }
}
