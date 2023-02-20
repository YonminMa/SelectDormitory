package com.pku.dormitory.service.impl;

import com.pku.dormitory.mapper.SysMapper;
import com.pku.dormitory.service.SysService;
import com.pku.dormitory.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yonmin
 * @date 2022/12/5 12:13
 */
@Service
public class SysServiceImpl implements SysService {

    @Autowired
    SysMapper sysMapper;

    @Override
    public Result getOpenTime(String accessToken) {
        return Result.ok()
                .data("start_time", sysMapper.getStartTime())
                .data("end_time", sysMapper.getEndTime());
    }

    @Override
    public Result getTeamLimit(String accessToken) {
        return Result.ok()
                .data("team_limit", sysMapper.getTeamLimit())
                .data("team_num", sysMapper.getTeamNum());
    }
}
