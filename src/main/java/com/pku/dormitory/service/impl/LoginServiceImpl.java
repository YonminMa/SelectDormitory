package com.pku.dormitory.service.impl;

import com.pku.dormitory.domain.Login;
import com.pku.dormitory.service.LoginService;
import com.pku.dormitory.service.StudentService;
import com.pku.dormitory.utils.JwtUtils;
import com.pku.dormitory.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    StudentService studentService;

    @Override
    public Result login(Login login) {
        String uid = login.getUid();
        String password = login.getPassword();
        if (studentService.checkStudent(uid, password)) {
            String token = JwtUtils.generateToken(uid);
            System.out.println(Objects.requireNonNull(JwtUtils.getClaimsByToken(token)).getSubject());
            return Result.ok().data("token", token);
        } else {
            System.out.println("用户名或密码错误");
            return Result.error();
        }
    }
}
