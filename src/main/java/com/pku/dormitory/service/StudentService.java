package com.pku.dormitory.service;

import com.pku.dormitory.domain.Student;

public interface StudentService {

    // 添加学生信息
    void saveStudent(Student student);

    // 根据uid查询student
    Student getStudent(String uid);

    // 输入密码时查询student是否存在
    Student checkStudent(String uid, String password);

    // 改变student的password
    void changePassword(String uid, String password);

    // 删除学生
    void deleteStudent(String uid);

    void updateRoomById(int id, String room);

    void updateRoomByGid(int gid, String room);
}
