package com.rahat.onlinelibrary.controllers;

import com.rahat.onlinelibrary.exception.BookNameAuthorNameAlreadyExistsExcepion;
import com.rahat.onlinelibrary.exception.EmailPasswordNotMatchException;
import com.rahat.onlinelibrary.exception.NoBooksFoundException;
import com.rahat.onlinelibrary.exception.UserAlreadyExistException;
import com.rahat.onlinelibrary.model.ExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({UserAlreadyExistException.class, EmailPasswordNotMatchException.class, BookNameAuthorNameAlreadyExistsExcepion.class, NoBooksFoundException.class})
    public ResponseEntity<Object> returnNotFoundException(Exception ex) {
        if (ex instanceof UserAlreadyExistException) {

            return new ResponseEntity<>(new ExceptionModel(ex.getMessage()), HttpStatus.NOT_IMPLEMENTED);

        } else if (ex instanceof EmailPasswordNotMatchException) {
            return new ResponseEntity<>(new ExceptionModel(ex.getMessage()), HttpStatus.NOT_FOUND);


        } else if (ex instanceof BookNameAuthorNameAlreadyExistsExcepion) {
            return new ResponseEntity<>(new ExceptionModel(ex.getMessage()), HttpStatus.BAD_REQUEST);


        } else if (ex instanceof NoBooksFoundException) {
            return new ResponseEntity<>(new ExceptionModel(ex.getMessage()), HttpStatus.BAD_REQUEST);


        } else {
            return new ResponseEntity<>(new ExceptionModel(ex.getMessage()), HttpStatus.NOT_IMPLEMENTED);

        }

    }
}
