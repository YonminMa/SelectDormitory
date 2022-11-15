package com.pku.dormitory.service.impl;

import com.pku.dormitory.dao.StudentRepository;
import com.pku.dormitory.domain.Student;
import com.pku.dormitory.mapper.StudentMapper;
import com.pku.dormitory.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentMapper studentMapper;

    @Override
    public void saveStudent(Student student) {
        // 由于继承了JpaRepository因此studentRepository自带save函数
        studentRepository.save(student);
    }

    @Override
    public Student getStudent(String uid) {
        return studentMapper.findByUid(uid);
    }

    @Override
    public boolean checkStudent(String uid, String password) {
        Student student = studentRepository.findByUidAndPassword(uid, password);
        return student != null;
    }

    @Override
    public void changePassword(String uid, String password) {
        studentRepository.updatePassword(uid, password);
    }

    @Override
    public void deleteStudent(String uid) {
        studentRepository.deleteStudentByUid(uid);
    }

    @Override
    public void updateRoomById(int id, String room) {
        studentRepository.updateRoomById(id, room);
    }

    @Override
    public void updateRoomByGid(int gid, String room) {
        studentRepository.updateRoomByGid(gid, room);
    }


}
