package com.malsum.catalogservice.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler
//    public ResponseEntity<ExceptionWrapper> notFoundException(NotFoundException exception){
//        ExceptionWrapper exceptionWrapper = new ExceptionWrapper(exception.getMessage(), HttpStatus.NOT_FOUND.value(),"Id not found in database;");
//        return new ResponseEntity<>(exceptionWrapper,HttpStatus.NOT_FOUND);
//    }
//
//    //Global Handler
//    @ExceptionHandler
//    public ResponseEntity<ExceptionWrapper> globalHandler(Exception exception){
//        ExceptionWrapper exceptionWrapper = new ExceptionWrapper(exception.getMessage(), HttpStatus.NOT_FOUND.value());
//        return new ResponseEntity<>(exceptionWrapper,HttpStatus.NOT_FOUND);
//    }

//
//    @Override
//    @ExceptionHandler
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", new Date());
//        body.put("status", status.value());
//
//        List<String> errors = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(x -> x.getDefaultMessage())
//                .collect(Collectors.toList());
//        body.put("errors", errors);
//        return new ResponseEntity<>(body,headers,status);
//    }
@ResponseStatus(HttpStatus.BAD_REQUEST)
@ExceptionHandler(MethodArgumentNotValidException.class)
public Map<String, String> handleValidationExceptions(
        MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
        String fieldName = ((FieldError) error).getField();
        String errorMessage = error.getDefaultMessage();
        errors.put(fieldName, errorMessage);
    });
    return errors;
}
}
