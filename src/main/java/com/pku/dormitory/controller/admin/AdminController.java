package com.pku.dormitory.controller.admin;

import com.pku.dormitory.domain.Admin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/admin")
public class AdminController {

    @GetMapping("/login1")
    public String login1(String user, String password){
        return user + password;
    }

    @GetMapping("/login2")
    // @RequestParam注解访问时变成/s?username=xxx，如果不加注解只能是/s?user=xxx
    public String login2(@RequestParam(value = "username", required = false) String user) {
        return user;
    }

    @GetMapping("/login3")
    // @RequestParam注解访问时变成/s?username=xxx，如果不加注解只能是/s?user=xxx
    public String login3(@RequestBody Admin admin) {
        System.out.println(admin);
        return "执行login3";
    }
}
