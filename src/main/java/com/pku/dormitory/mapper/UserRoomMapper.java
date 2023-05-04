package com.pku.dormitory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pku.dormitory.domain.UserRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Yonmin
 * @date 2022/12/4 16:52
 */
@Mapper
public interface UserRoomMapper extends BaseMapper<UserRoom> {

    @Select("select exists(select 1 from user_room where user_id = #{userId})")
    Boolean existsUserId(Integer userId);

    void insertBatch(List<UserRoom> userRoomList);
}
