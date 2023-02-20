package com.pku.dormitory.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author Yonmin
 * @date 2022/12/4 13:23
 */
@TableName("building")
public class Building {
    /**
     * 主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 楼名称
     */
    private String name;
    /**
     * 描述，可支持富文本，用户可见
     */
    private String description;
    /**
     * 图片地址，仅支持一张图片
     */
    private String imageUrl;
}
