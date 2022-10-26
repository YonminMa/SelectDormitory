package com.pku.dormitory.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // 添加Entity可以根据@Table(name = "tb_student")中的name字段自动创建表
@Table(name = "tb_student")
@TableName("tb_student") // MyBatisPlus注解，参数为表名，用于设置实体类所对应的表名自动装箱
public class Student {
    @Id
    @TableId(type = IdType.AUTO) // MyBatisPlus设置自增主键的注解
    private int id;

    private String uid;
    private String name;
    private int gender;
    private String password;
    private int gid;
    private String room;

    public Student(String uid, String name, int gender, String password, int gid, String room) {
        this.uid = uid;
        this.name = name;
        this.gender = gender;
        this.password = password;
        this.gid = gid;
        this.room = room;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Student{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", password='" + password + '\'' +
                ", gid=" + gid +
                ", room=" + room +
                '}';
    }
}