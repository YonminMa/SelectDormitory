package com.pku.dormitory.service;

import com.pku.dormitory.utils.Result;

/**
 * @author Yonmin
 * @date 2022/12/5 12:12
 */
public interface SysService {
    Result getOpenTime(String accessToken);

    Result getTeamLimit(String accessToken);
}
