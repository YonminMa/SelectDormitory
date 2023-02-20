package com.pku.dormitory.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Yonmin
 * @date 2022/11/24 18:31
 */
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
public class User {
    private String username;
    private String password;
}
