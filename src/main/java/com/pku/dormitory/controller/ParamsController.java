package com.pku.dormitory.controller;

import com.pku.dormitory.domain.Admin;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParamsController {

    @GetMapping("/getTest1")
    public String getTest1(){
        return "Get请求";
    }

    @GetMapping("/getTest2")
    public String getTest2(String username, String phone){
        System.out.println("username"+username);
        System.out.println("phone"+phone);
        return "Get请求";
    }

    @GetMapping("/getTest3")
    public String getTest3(@RequestParam("name") String username, String phone){
        System.out.println("username"+username);
        System.out.println("phone"+phone);
        return "Get请求";
    }

    @PostMapping("/postTest1")
    public String postTest1(){
        return "Post请求";
    }

    // 传入的参数在Body中
    @PostMapping("/postTest2")
    public String postTest2(String username, String phone){
        System.out.println("username"+username);
        System.out.println("phone"+phone);
        return "Post请求";
    }

    // 输入为对象的时候前端Post的参数名要和后端Admin类的属性名一样
    // 前端post的值自动封装为Admin类型的对象
    @PostMapping("/postTest3")
    public String postTest3(Admin admin){
        System.out.println(admin);
        return "Post请求";
    }

    // @RequestBody注解表示接受json类型的数据
    @PostMapping("/postTest4")
    public String postTest4(@RequestBody Admin admin){
        System.out.println(admin);
        return "Post请求";
    }

    @GetMapping("/test/**")
    public String getTest4(){
        return "通配符请求";
    }
}
