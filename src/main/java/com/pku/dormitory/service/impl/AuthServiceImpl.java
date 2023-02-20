package com.pku.dormitory.service.impl;

import com.pku.dormitory.domain.User;
import com.pku.dormitory.service.AuthService;
import com.pku.dormitory.utils.JwtUtils;
import com.pku.dormitory.utils.Result;
import com.pku.dormitory.utils.TokenConstant;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author Yonmin
 * @date 2022/11/24 19:09
 */
@Service
public class AuthServiceImpl implements AuthService {

    private Result generateToken(String username) {
        String accessToken = JwtUtils.generateToken(username, TokenConstant.ACCESS_EXPIRE);
        String tokenType = "bearer";
        int expiresIn = (int) TokenConstant.ACCESS_EXPIRE;
        String scope = "app";
        String refreshToken = JwtUtils.generateToken(username, TokenConstant.REFRESH_EXPIRE);


        return Result.ok()
                .data("access_token", accessToken)
                .data("token_type", tokenType)
                .data("expire_in", expiresIn)
                .data("scope", scope)
                .data("refresh_token", refreshToken);
    }

    @Override
    public Result login(User login) {
        String username = login.getUsername();
        String password = login.getPassword();
        System.out.println(username);
        System.out.println(password);

        return generateToken(username);
    }

    @Override
    public Result logout(String accessToken) {
        return Result.ok();
    }

    @Override
    public Result refreshToken(HashMap<String, String> params) {
        String refreshToken = params.get("refresh_token");
        String username = JwtUtils.getClaimsByToken(refreshToken).getSubject();
        return generateToken(username);
    }
}
