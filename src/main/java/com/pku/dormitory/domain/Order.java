package com.pku.dormitory.domain;

import java.io.Serializable;

public class Order implements Serializable {
    private int oid;

    private int bid;

    private int type;

    private int gender;

    public Order() {}

    public Order(int oid, int bid, int type, int gender) {
        this.oid = oid;
        this.bid = bid;
        this.type = type;
        this.gender = gender;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
