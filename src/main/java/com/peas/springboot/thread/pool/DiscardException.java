package com.peas.springboot.thread.pool;

public class DiscardException extends RuntimeException{
    public DiscardException(String message) {
        super(message);
    }
}
