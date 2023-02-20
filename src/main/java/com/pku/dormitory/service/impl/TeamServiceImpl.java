package com.pku.dormitory.service.impl;

import com.pku.dormitory.domain.Team;
import com.pku.dormitory.domain.UserTeam;
import com.pku.dormitory.mapper.*;
import com.pku.dormitory.service.TeamService;
import com.pku.dormitory.utils.JwtUtils;
import com.pku.dormitory.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Yonmin
 * @date 2022/12/2 15:03
 */
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    InfoMapper infoMapper;

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    UserTeamMapper userTeamMapper;

    @Autowired
    SysMapper sysMapper;

    /**
     * 创建队伍
     *
     * @param accessToken
     * @param params      params.get("name") 队伍名
     *                    params.get("description") 队伍描述
     * @return 成功 | 已创建队伍 | 不允许组队
     */
    @Override
    public Result createTeam(String accessToken, HashMap<String, String> params) {

        String username = JwtUtils.getClaimsByToken(accessToken).getSubject();
        Integer userId = userMapper.getIdByUsername(username);

        if (!userTeamMapper.existsUser(userId)) {
            String name = params.get("name");
            String description = params.get("description");

            String inviteCode = generateInviteCode(username);

            // 插入到team表
            Team team = new Team();
            team.setName(name);
            team.setDescription(description);
            team.setInviteCode(inviteCode);
            teamMapper.insert(team);

            // 插入到user_team表
            UserTeam userTeam = new UserTeam();
            userTeam.setUserId(userId);
            userTeam.setTeamId(team.getId());
            userTeam.setIsLeader(1);
            userTeamMapper.insert(userTeam);

            return Result.ok().data("invite_code", inviteCode);
        } else {
            return Result.error("用户已创建队伍");
        }

    }

    /**
     * 删除队伍
     * 只有队长可以删除队伍
     * 删除整个队伍，无论队伍里是否只有一个人
     *
     * @param accessToken 获取想要删除队伍的用户accessToken
     * @return 删除结果Result
     */
    @Override
    public Result deleteTeam(String accessToken) {

        String username = JwtUtils.getClaimsByToken(accessToken).getSubject();
        Integer userId = userMapper.getIdByUsername(username);

        // 获取要删除的teamId
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        map.put("is_leader", 1);
        List<UserTeam> userTeams = userTeamMapper.selectByMap(map);

        if (userTeams.size() != 0) {

            Integer teamId = userTeams.get(0).getTeamId();

            // 删除user_team表中的内容
            map = new HashMap<>();
            map.put("team_id", teamId);
            userTeamMapper.deleteByMap(map);

            // 删除team表中的内容
            teamMapper.deleteById(teamId);

            return Result.ok();
        } else {
            return Result.error("没有查询到你的队伍");
        }

    }

    /**
     * 加入队伍
     *
     * @param accessToken 要加入队伍的学生
     * @param params      params.get("invite_code")队伍邀请码
     * @return 成功 | 没有此队伍、队伍满人、已加入队伍
     */
    @Override
    public Result joinTeam(String accessToken, HashMap<String, String> params) {

        String username = JwtUtils.getClaimsByToken(accessToken).getSubject();
        Integer userId = userMapper.getIdByUsername(username);
        if (userTeamMapper.getUserTeamsByUserId(userId).size() != 0) {
            return Result.error("已加入队伍");
        }

        String inviteCode = params.get("invite_code");

        Map<String, Object> map = new HashMap<>();
        map.put("invite_code", inviteCode);

        List<Team> teams = teamMapper.selectByMap(map);

        if (teams.size() == 0) {
            return Result.error("没有此队伍");
        }

        Team team = teams.get(0);
        Integer teamNum = Integer.valueOf(sysMapper.getTeamNum());
        if (team.getMemberCount() >= teamNum) {
            return Result.error("队伍满人");
        }

        Integer teamId = team.getId();

        UserTeam userTeam = new UserTeam();
        userTeam.setUserId(userId);
        userTeam.setTeamId(teamId);
        userTeamMapper.insert(userTeam);

        team.setMemberCount(team.getMemberCount() + 1);
        teamMapper.updateById(team);

        return Result.ok().data("team_id", teamId);
    }

    /**
     * 退出队伍
     * 队长须先转让才能够退出队伍
     *
     * @param accessToken 要加入队伍的学生
     * @return 成功 | 未加入队伍 | 队长无法退出队伍
     */
    @Override
    public Result quitTeam(String accessToken) {

        String username = JwtUtils.getClaimsByToken(accessToken).getSubject();
        Integer userId = userMapper.getIdByUsername(username);

        List<UserTeam> userTeams = userTeamMapper.getUserTeamsByUserId(userId);

        if (userTeams.size() == 0) return Result.error("未加入队伍");

        UserTeam userTeam = userTeams.get(0);
        if (userTeam.getIsLeader() == 1) return Result.error("队长无法退出队伍");

        // 删除user_team表中的内容
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        userTeamMapper.deleteByMap(map);

        // 更新team表的member_count
        Team team = teamMapper.selectById(userTeam.getTeamId());
        team.setMemberCount(team.getMemberCount() - 1);
        teamMapper.updateById(team);

        return Result.ok();
    }

    /**
     * 获取我所在队伍的信息
     *
     * @param accessToken 要获取队伍信息的学生
     * @return 成功 | 未加入队伍
     */
    @Override
    public Result getMyTeam(String accessToken) {

        String username = JwtUtils.getClaimsByToken(accessToken).getSubject();
        Integer userId = userMapper.getIdByUsername(username);

        List<UserTeam> userTeams = userTeamMapper.getUserTeamsByUserId(userId);

        if (userTeams.size() == 0) return Result.error("未加入队伍");

        Integer teamId = userTeams.get(0).getTeamId();
        String teamName = teamMapper.getNameById(teamId);
        String inviteCode = teamMapper.getInviteCodeById(teamId);

        List<Integer> userIds = userTeamMapper.getUserIdsByTeamId(teamId);

        List<HashMap<String, Object>> mapList = new ArrayList<>();
        HashMap<String, Object> map;
        for (Integer id : userIds) {
            map = new HashMap<>();
            map.put("student_id", id);
            map.put("student_name", infoMapper.selectNameById(id));
            mapList.add(map);
        }

        return Result.ok()
                .data("group_id", teamId)
                .data("group_name", teamName)
                .data("invite_code", inviteCode)
                .data("members", mapList);
    }

    /**
     * 转让队长
     * 转让人必须是队长
     * 被转让人必须是队伍中的人
     * 转让队长成功后需要重新生成邀请码防止重复
     *
     * @param accessToken 要转让队长的学生
     * @param params      params.get("username")被转让人学号
     * @return 成功 | 你没有队伍 | 被转让人不是队伍中的人 | 你不是队长 | 无需转让给自己
     */
    @Override
    public Result transferLeader(String accessToken, HashMap<String, String> params) {

        String username = JwtUtils.getClaimsByToken(accessToken).getSubject();
        Integer userId = userMapper.getIdByUsername(username);

        List<UserTeam> userTeams = userTeamMapper.getUserTeamsByUserId(userId);

        if (userTeams.size() == 0) return Result.error("你没有队伍");

        UserTeam userTeam = userTeams.get(0);
        if (userTeam.getIsLeader() == 0) return Result.error("你不是队长");

        // 获取被转移人的userId和username
        String transferredName = params.get("username");
        Integer transferredId = userMapper.getIdByUsername(transferredName);

        if (Objects.equals(userId, transferredId)) return Result.error("无需转让给自己");

        if (!userTeamMapper.getUserIdsByTeamId(userTeam.getTeamId()).contains(transferredId)) {
            return Result.error("被转让人不是队伍中的人");
        }

        Integer teamId = userTeam.getTeamId();
        String inviteCode = generateInviteCode(transferredName);

        // 开始转移队长
        userTeamMapper.updateIsLeaderByUserId(userId, 0);
        userTeamMapper.updateIsLeaderByUserId(transferredId, 1);
        teamMapper.updateInviteCodeById(teamId, inviteCode);

        return Result.ok();
    }

    /**
     * 根据用户名生成邀请码
     *
     * @param username 用户名
     * @return 生成的邀请码
     */
    private String generateInviteCode(String username) {
        int bias = new Random().nextInt(16) + 17;
        char[] chars = username.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) ((int) chars[i] + bias);
        }
        return String.valueOf(chars);
    }
}
