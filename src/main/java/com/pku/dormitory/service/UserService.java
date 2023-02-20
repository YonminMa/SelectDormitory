package com.pku.dormitory.service;

import com.pku.dormitory.utils.Result;

import java.util.HashMap;

/**
 * @author Yonmin
 * @date 2022/11/26 14:21
 */
public interface UserService {

    Result getInfo(String accessToken);

    Result changePasswd(String accessToken, HashMap<String, String> params);

    Result getRoom(String accessToken);
}
