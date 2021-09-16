package com.malsum.shoppingsessionservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
//public class CustomHandler{
//
//    //Problem: need to check which HttpStatus applies and when
//
//    @ExceptionHandler
//    public ResponseEntity<ExceptionWrapper> notFoundException(NotFoundException exception){
//        ExceptionWrapper exceptionWrapper = new ExceptionWrapper(exception.getMessage(), HttpStatus.NOT_FOUND.value(),"Id not found in database;");
//        return new ResponseEntity<>(exceptionWrapper,HttpStatus.NOT_FOUND);
//    }
//
//    //Global Handler
//    @ExceptionHandler
//    public ResponseEntity<ExceptionWrapper> globalHandler(Exception exception){
//        ExceptionWrapper exceptionWrapper = new ExceptionWrapper(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
//        return new ResponseEntity<>(exceptionWrapper,HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<ExceptionWrapper> notEnoughStock(NotEnoughStockException exception){
//        ExceptionWrapper exceptionWrapper = new ExceptionWrapper(exception.getMessage(), HttpStatus.PRECONDITION_FAILED.value(),"Not enough stock available");
//        return new ResponseEntity<>(exceptionWrapper,HttpStatus.PRECONDITION_FAILED);
//    }
//}
