package com.pku.dormitory.controller;

import com.pku.dormitory.service.SysService;
import com.pku.dormitory.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yonmin
 * @date 2022/12/5 12:11
 */
@RestController
@RequestMapping("/sys")
public class SysController {

    @Autowired
    SysService sysService;

    @GetMapping("/time")
    public Result getOpenTime(@RequestAttribute(name = "access_token") String accessToken) {
        return sysService.getOpenTime(accessToken);
    }

    @GetMapping("/team")
    public Result getTeamLimit(@RequestAttribute(name = "access_token") String accessToken) {
        return sysService.getTeamLimit(accessToken);
    }
}
