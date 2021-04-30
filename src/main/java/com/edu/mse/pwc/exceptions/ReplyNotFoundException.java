package com.edu.mse.pwc.exceptions;


public class ReplyNotFoundException extends RuntimeException {

    public ReplyNotFoundException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public ReplyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
