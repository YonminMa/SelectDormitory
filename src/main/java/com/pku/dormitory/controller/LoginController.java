package com.pku.dormitory.controller;

import com.pku.dormitory.domain.Student;
import com.pku.dormitory.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@RestController
public class LoginController {
    @Autowired
    StudentService studentService;

    @GetMapping
    public String loginPage(){
        return "/login";
    }

    @PostMapping("/login")
    @ResponseBody // 使得返回对象是json格式
    public Student login(@RequestParam String uid,
                        @RequestParam String password,
                        HttpSession session){
        Student student = studentService.checkStudent(uid, password);
        if (student != null) {
            student.setPassword(null);
            session.setAttribute("student", student);
            return student;
        } else {
            return null;
        }
    }

    @PostMapping("/check")
    @ResponseBody // 使得返回对象是json格式
    public String fixLogin(@RequestParam String uid,
                           @RequestParam String password){
        Student student = studentService.checkStudent(uid, password);
        if (student != null){
            return null;
        }
        return "用户名或密码错误";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
