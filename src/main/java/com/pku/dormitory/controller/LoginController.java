package com.pku.dormitory.controller;

import com.pku.dormitory.domain.Login;
import com.pku.dormitory.service.LoginService;
import com.pku.dormitory.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @GetMapping
    public String loginPage(){
        return "/login";
    }

    @PostMapping("/login")
    @ResponseBody // 使得返回对象是json格式
    public Result login(@RequestBody Login login){
        return loginService.login(login);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
