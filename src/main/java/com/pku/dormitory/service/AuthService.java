package com.pku.dormitory.service;

import com.pku.dormitory.domain.User;
import com.pku.dormitory.utils.Result;

import java.util.HashMap;

/**
 * @author Yonmin
 * @date 2022/11/24 19:07
 */
public interface AuthService {
    /**
     * 登录接口
     *
     * @param login 登录信息
     * @return 登录结果
     */
    Result login(User login);

    /**
     * 登出接口
     *
     * @return 登出结果
     */
    Result logout(String accessToken);

    /**
     * 刷新token信息
     *
     * @return 新的token信息
     */
    Result refreshToken(HashMap<String, String> params);
}
