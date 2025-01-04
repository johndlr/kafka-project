package com.juandlr.emailnotification.exception;

public class NoRetryableException extends RuntimeException{
    public NoRetryableException(String message) {
        super(message);
    }

    public NoRetryableException(Throwable cause) {
        super(cause);
    }
}
