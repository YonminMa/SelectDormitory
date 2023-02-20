package com.pku.dormitory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pku.dormitory.domain.Info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Yonmin
 * @date 2022/12/3 20:43
 */
@Mapper
public interface InfoMapper extends BaseMapper<Info> {

    @Select("select name from info where id = #{userId}")
    String selectNameById(Integer userId);

    @Select("select gender from info where id = #{userId}")
    Integer getGenderById(Integer userId);
}
