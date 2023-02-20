package com.pku.dormitory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pku.dormitory.domain.UserTeam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Yonmin
 * @date 2022/12/3 12:05
 */
@Mapper
public interface UserTeamMapper extends BaseMapper<UserTeam> {

    @Select("SELECT EXISTS(SELECT 1 FROM user_team WHERE user_id = #{useId})")
    Boolean existsUser(Integer userId);

    @Select("select * from user_team where user_id = #{userId}")
    List<UserTeam> getUserTeamsByUserId(Integer userId);

    @Select("select user_id from user_team where team_id = #{teamId}")
    List<Integer> getUserIdsByTeamId(Integer teamId);

    @Select("select team_id from user_team where user_id = #{userId}")
    Integer getTeamIdByUserId(Integer userId);

    @Update("update user_team set is_leader = #{isLeader} where user_id = #{userId}")
    void updateIsLeaderByUserId(Integer userId, Integer isLeader);
}
