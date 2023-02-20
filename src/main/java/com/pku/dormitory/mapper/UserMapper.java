package com.pku.dormitory.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Yonmin
 * @date 2022/11/26 14:25
 */
@Mapper
public interface UserMapper {
    @Select("select name, gender, email, tel, last_login_time, verification_code, class_name, type  from user u " +
            "inner join info i on u.id = i.id " +
            "where u.username = #{username}")
    HashMap<String, Object> getInfo(String username);

    @Select("select password from user where username = #{username}")
    String getPwdByUsername(String username);

    @Select("select id from user where username = #{username}")
    Integer getIdByUsername(String username);

    @Update("update user u set u.password = #{password} where u.username = #{username}")
    void changePasswd(String username, String password);

    @Select("select room_id from user_room where user_id = #{id}")
    Integer getRidById(Integer id);

    @Select("select name from room where id = #{rid}")
    String getRoomNameByRid(Integer rid);

    @Select("select i.name from " +
            "(select user_id id from user_room where room_id = #{rid}) u " +
            "left join info i on i.id = u.id")
    ArrayList<String> getStudentNameByRid(Integer rid);

    @Select("select gender from info where id = #{id}")
    Integer getGenderById(Integer id);

}
