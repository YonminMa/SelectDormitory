package com.pku.dormitory.controller;

import com.pku.dormitory.service.UserService;
import com.pku.dormitory.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author Yonmin
 * @date 2022/11/26 14:20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/info")
    public Result getInfo(@RequestAttribute(name = "access_token") String accessToken) {
        return userService.getInfo(accessToken);
    }

    @PutMapping("/passwd")
    public Result changePasswd(@RequestAttribute(name = "access_token") String accessToken,
                               @RequestBody HashMap<String, String> params) {
        return userService.changePasswd(accessToken, params);
    }

    @GetMapping("/room")
    public Result getRoom(@RequestAttribute(name = "access_token") String accessToken) {
        return userService.getRoom(accessToken);
    }
}
