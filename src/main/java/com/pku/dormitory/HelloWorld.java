package com.pku.dormitory;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @RequestMapping
    public String test() {
        return "He llo fdsfd";
    }
}
