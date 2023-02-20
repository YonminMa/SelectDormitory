package com.pku.dormitory.service;

import com.pku.dormitory.utils.Result;

import java.util.HashMap;

/**
 * @author Yonmin
 * @date 2022/12/2 15:03
 */
public interface TeamService {

    Result createTeam(String accessToken, HashMap<String, String> params);

    Result deleteTeam(String accessToken);

    Result joinTeam(String accessToken, HashMap<String, String> params);

    Result quitTeam(String accessToken);

    Result getMyTeam(String accessToken);

    Result transferLeader(String accessToken, HashMap<String, String> params);
}
