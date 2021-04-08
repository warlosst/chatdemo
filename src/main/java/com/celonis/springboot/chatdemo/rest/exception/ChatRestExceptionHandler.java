package com.celonis.springboot.chatdemo.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ChatRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ChatErrorResponse> handleBadRequestException(Exception ex){

        ChatErrorResponse error = new ChatErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler
    public ResponseEntity<ChatErrorResponse> handleNotFoundException(NotFoundException ex){

        ChatErrorResponse error = new ChatErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    public ResponseEntity<ChatErrorResponse> handleBadArgumentsException(MethodArgumentNotValidException ex){
        ChatErrorResponse error = new ChatErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getFieldError().getDefaultMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ChatErrorResponse> handleNotAuthorizedException(NotAuthorizedException ex){
        ChatErrorResponse error = new ChatErrorResponse();

        error.setStatus(HttpStatus.FORBIDDEN.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

}
