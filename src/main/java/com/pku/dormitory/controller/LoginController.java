package com.pku.dormitory.controller;

import com.pku.dormitory.domain.Student;
import com.pku.dormitory.service.StudentService;
import com.pku.dormitory.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
    public Student login(@RequestBody Student student){
        String uid = student.getUid();
        String password = student.getPassword();

        student.setToken(JwtUtils.generateToken(uid));
        return student;
//        return studentService.checkStudent(uid, password);
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
