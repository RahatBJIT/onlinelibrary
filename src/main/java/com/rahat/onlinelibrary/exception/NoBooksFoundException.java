package com.rahat.onlinelibrary.exception;

public class NoBooksFoundException extends RuntimeException{
    public NoBooksFoundException(String message){
        super(message);
    }
}