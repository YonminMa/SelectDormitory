package com.pku.dormitory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pku.dormitory.domain.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Yonmin
 * @date 2022/12/2 15:03
 */
@Mapper
public interface TeamMapper extends BaseMapper<Team> {

    @Select("select invite_code from team where id = #{id}")
    String getInviteCodeById(Integer id);

    @Select("select name from team where id = #{id}")
    String getNameById(Integer id);

    @Update("update team set invite_code = #{inviteCode} where id = #{id}")
    void updateInviteCodeById(Integer id, String inviteCode);
}
