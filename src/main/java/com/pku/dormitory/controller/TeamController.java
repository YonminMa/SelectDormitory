package com.pku.dormitory.controller;

import com.pku.dormitory.service.TeamService;
import com.pku.dormitory.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author Yonmin
 * @date 2022/12/2 14:59
 */
@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @PostMapping("/create")
    public Result createTeam(@RequestAttribute(name = "access_token") String accessToken,
                             @RequestBody HashMap<String, String> params) {
        return teamService.createTeam(accessToken, params);
    }

    @DeleteMapping("/del")
    public Result deleteTeam(@RequestAttribute(name = "access_token") String accessToken) {
        return teamService.deleteTeam(accessToken);
    }

    @PostMapping("/join")
    public Result joinTeam(@RequestAttribute(name = "access_token") String accessToken,
                           @RequestBody HashMap<String, String> params) {
        return teamService.joinTeam(accessToken, params);
    }

    @GetMapping("/quit")
    public Result quitTeam(@RequestAttribute(name = "access_token") String accessToken) {
        return teamService.quitTeam(accessToken);
    }

    @GetMapping("/my")
    public Result getMyTeam(@RequestAttribute(name = "access_token") String accessToken) {
        return teamService.getMyTeam(accessToken);
    }

    @PostMapping("/transfer")
    public Result transferLeader(@RequestAttribute(name = "access_token") String accessToken,
                                 @RequestBody HashMap<String, String> params) {
        return teamService.transferLeader(accessToken, params);
    }

}
