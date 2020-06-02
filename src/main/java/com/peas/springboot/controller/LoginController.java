package com.peas.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

@Controller
public class LoginController {
    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password")String password,
                        Map<String,Object> map, HttpSession session){
        session.setAttribute("loginUser",username);
        if(!StringUtils.isEmpty(username) && Objects.equals("123456",password)){
            return "redirect:/main.html";
        }else{
            map.put("msg","用户名密码错误");
            return "index";
        }
    }
}
