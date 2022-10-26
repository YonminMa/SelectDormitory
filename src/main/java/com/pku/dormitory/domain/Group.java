package com.pku.dormitory.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_group")
public class Group {

    @Id
    private int id;

    // 人数
    private int count;

    // 性别
    private int gender;

    public Group() {
    }

    public Group(int id, int count, int gender) {
        this.id = id;
        this.count = count;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", count=" + count +
                ", gender=" + gender +
                '}';
    }
}
