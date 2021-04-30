package com.edu.mse.pwc.exceptions;

@SuppressWarnings("unused")
public class TopicNotFoundException extends RuntimeException {

    public TopicNotFoundException(String message) {
        super(message);
    }

    public TopicNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
