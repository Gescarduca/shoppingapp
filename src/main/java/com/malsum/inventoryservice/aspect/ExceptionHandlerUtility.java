package com.malsum.inventoryservice.aspect;

import com.malsum.inventoryservice.exceptions.ItemNotFound;
import com.malsum.inventoryservice.exceptions.ResponseWrapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerUtility extends ResponseEntityExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<ResponseWrapper> itemNotFound(ItemNotFound itemNotFoundException){
        ResponseWrapper responseWrapper = new ResponseWrapper(itemNotFoundException.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(responseWrapper,HttpStatus.NOT_FOUND);
    }

    //override the mehtod of super class this is in case user passes values not valid for example a name with only 2 characters
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        ResponseWrapper responseWrapper = new ResponseWrapper("Validation failed", HttpStatus.BAD_REQUEST.value());
//        return new ResponseEntity<>(responseWrapper,HttpStatus.BAD_REQUEST);
//
//        //return super.handleMethodArgumentNotValid(ex, headers, status, request);
//    }

    @ExceptionHandler
    public ResponseEntity<ResponseWrapper> globalExceptions(Exception exception){
        ResponseWrapper wrapper = new ResponseWrapper(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        System.out.println(exception.getMessage());
        return new ResponseEntity<>(wrapper,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
