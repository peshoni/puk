package com.edu.mse.pwc.exceptions;

public class DuplicateTopicException extends RuntimeException {

    public DuplicateTopicException(String message) {
        super(message);
    }

    @SuppressWarnings("unused")
    public DuplicateTopicException(String message, Throwable cause) {
        super(message, cause);
    }

}