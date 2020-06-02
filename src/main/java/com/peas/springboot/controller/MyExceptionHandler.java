package com.peas.springboot.controller;

import com.peas.springboot.exception.UserNotExitsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(UserNotExitsException.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        request.setAttribute("javax.servlet.error.status_code",400);
        map.put("code","user.notExits");
        map.put("message",e.getMessage());
        request.setAttribute("ext",map);
        return "forward: /error";
    }
}
