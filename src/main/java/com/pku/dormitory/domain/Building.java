package com.pku.dormitory.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "building")
public class Building {
    @Id
    private int id;

    // 宿舍楼号
    private int number;

    // 剩余床位数
    private int rest;

    public Building() {
    }

    public Building(int number, int rest) {
        this.number = number;
        this.rest = rest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getRest() {
        return rest;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                ", number=" + number +
                ", rest=" + rest +
                '}';
    }
}
