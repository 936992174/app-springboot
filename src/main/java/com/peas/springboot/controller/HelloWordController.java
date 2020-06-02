package com.peas.springboot.controller;

import com.peas.springboot.exception.UserNotExitsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Objects;

@Controller
public class HelloWordController {
    @RequestMapping("/helloWord")
    @ResponseBody
    public String helloWord(@RequestParam("userName") String userName){
        if(Objects.equals("aaa",userName)){
            throw new UserNotExitsException();
        }
        return "hello word";
    }
    @RequestMapping("success")
    public String success(Map<String,Object> map){
        map.put("hello","你好");
        return "success";
                }
                }
