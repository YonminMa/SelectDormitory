package com.pku.dormitory.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Yonmin
 * @date 2022/12/2 17:02
 */
@Data
@TableName("team")
public class Team {

    //主键，自增
    @TableId(type = IdType.AUTO)
    private Integer id;
    //队伍名称
    @TableField("name")
    private String name;

    //加入口令
    @TableField("invite_code")
    private String inviteCode;

    @TableField("member_count")
    private Integer memberCount;

    //队伍描述，用户可见
    @TableField("description")
    private String description;

    //如果改组分配宿舍成功，置为1，将不可以再进行队伍的操作
    private Integer status;

}
