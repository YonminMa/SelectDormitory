package com.pku.dormitory.dao;


import com.pku.dormitory.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("select u from Student u where u.uid = ?1")
    Student findByUid(String uid);

    Student findByUidAndPassword(String uid, String password);
    @Transactional // 支持事务
    @Modifying // 涉及改
    @Query("update Student s set s.password = ?2 where s.uid = ?1")
    void updatePassword(String uid, String password);

    @Transactional
    @Modifying
    void deleteStudentByUid(String uid);
}