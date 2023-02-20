package com.pku.dormitory.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Yonmin
 * @date 2022/12/4 19:17
 */
@Data
@TableName("orders")
public class Order implements Serializable {
    private static final long serialVersionUID = 324620869928533167L;
    /**
     * 主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 提交人，user表中的id
     */
    private Integer uid;
    /**
     * groups表中的id，如果未组队，该值为0
     */
    private Integer groupId;
    /**
     * room表中的id
     */
    private Integer roomId;
    /**
     * building表中的id
     */
    private Integer buildingId;
    /**
     * 下单时间，用户提交表单时间
     */
    private Timestamp submitTime;
    /**
     * 订单生成时间，仅后台可见
     */
    private Timestamp createTime;
    /**
     * 订单完成时间，仅后台可见
     */
    private Timestamp finishTime;
    /**
     * 0未处理，1处理成功，2处理失败
     */
    private Integer status;

}

