package com.pku.dormitory.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "tb_result")
public class Result {

    @Id
    private int id;

    private int oid; // 学生的id或组队的gid

    private int type; // type为0时代表为学生，type为1时代表组队

    private int rid; // 房间的id

    private Timestamp timestamp; // 订单生成的时间

    public Result() {
    }

    public Result(int oid, int type, int rid) {
        this.oid = oid;
        this.type = type;
        this.rid = rid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", oid=" + oid +
                ", type=" + type +
                ", rid=" + rid +
                ", timestamp=" + timestamp +
                '}';
    }
}
