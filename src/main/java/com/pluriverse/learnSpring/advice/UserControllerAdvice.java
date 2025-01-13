package com.pluriverse.learnSpring.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UserControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message: ", ex.getMessage());
        responseBody.put("code: ", " 500");
        responseBody.put("time: ", LocalDate.now().toString());
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }
}
