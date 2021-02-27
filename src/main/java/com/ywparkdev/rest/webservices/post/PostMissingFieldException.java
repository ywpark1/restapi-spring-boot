package com.ywparkdev.rest.webservices.post;

public class PostMissingFieldException extends RuntimeException{
    public PostMissingFieldException(String message) {
        super(message);
    }
}
