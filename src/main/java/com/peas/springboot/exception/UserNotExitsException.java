package com.peas.springboot.exception;

public class UserNotExitsException extends RuntimeException {
    public UserNotExitsException() {
        super("用户不存在");
    }
}
