package com.pku.dormitory.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Yonmin
 * @date 2022/12/5 12:08
 */
@Data
@TableName("sys")
public class Sys {
    /**
     * 主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 配置名称
     */
    private String keyName;
    /**
     * 配置值
     */
    private String keyValue;
}
