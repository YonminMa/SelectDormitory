package com.pku.dormitory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pku.dormitory.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

// extends BaseMapper<Student>使用MyBatisPlus提供的方法
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    // 表明必须是数据库中表的名字
    @Select("select * from tb_student u where u.uid = #{uid}")
    Student findByUid(String uid);

    @Select("select * from student u where u.uid = #{uid} and u.uid = #{password}")
    Student findByUidAndPassword(String uid, String password);
}
