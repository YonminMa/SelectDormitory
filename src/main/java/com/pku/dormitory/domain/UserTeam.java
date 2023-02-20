package com.pku.dormitory.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Yonmin
 * @date 2022/12/3 12:03
 */
@Data
@TableName("user_team")
public class UserTeam {

    /**
     * 主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * user表中的id
     */
    private Integer userId;
    /**
     * 1是队长，0不是队长
     */
    private Integer isLeader;
    /**
     * team表中的id
     */
    private Integer teamId;
    /**
     * 加入时间
     */
    private Object joinTime;

}
