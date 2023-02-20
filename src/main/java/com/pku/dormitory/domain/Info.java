package com.pku.dormitory.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Yonmin
 * @date 2022/11/26 14:35
 */
@Getter
@Setter
@ToString
@TableName("info")
public class Info {

    @TableId(type = IdType.AUTO)
    private Integer id;
    //姓名
    private String name;
    //性别：0为女 1为男
    private Integer gender;
    //电子邮箱
    private String email;
    //电话号码
    private String tel;
    //最后一次登录时间
    private Integer lastLoginTime;

    private String verificationCode;

    private String className;
    //1为学生。此字段保留方便以后扩展
    private Integer type;

    public Info(String name, Integer gender, String email, String tel, Integer lastLoginTime, String verificationCode, String className, Integer type) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.tel = tel;
        this.lastLoginTime = lastLoginTime;
        this.verificationCode = verificationCode;
        this.className = className;
        this.type = type;
    }
}
