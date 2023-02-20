package com.pku.dormitory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pku.dormitory.domain.Sys;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Yonmin
 * @date 2022/12/5 12:08
 */
@Mapper
public interface SysMapper extends BaseMapper<Sys> {

    @Select("select s.key_value from sys s where s.key_name = 'start_time'")
    String getStartTime();

    @Select("select s.key_value from sys s where s.key_name = 'end_time'")
    String getEndTime();

    @Select("select s.key_value from sys s where s.key_name = 'team_limit'")
    String getTeamLimit();

    @Select("select s.key_value from sys s where s.key_name = 'team_num'")
    String getTeamNum();
}
