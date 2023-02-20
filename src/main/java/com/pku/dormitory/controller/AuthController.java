package com.pku.dormitory.controller;

import com.pku.dormitory.domain.User;
import com.pku.dormitory.service.AuthService;
import com.pku.dormitory.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author Yonmin
 * @date 2022/11/24 18:28
 */
@Api(tags = "登录")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public Result login(@RequestBody User login) {
        return authService.login(login);
    }

    @GetMapping("/logout")
    public Result logout(@RequestAttribute(name = "access_token") String accessToken) {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//                .getRequest();
        return authService.logout(accessToken);
    }

    @PostMapping("/refresh")
    public Result refreshToken(@RequestBody HashMap<String, String> params) {
        return authService.refreshToken(params);
    }

}
