package com.rahat.onlinelibrary.exception;

public class EmailPasswordNotMatchException extends RuntimeException{
    public EmailPasswordNotMatchException(String message){
        super(message);
    }
}