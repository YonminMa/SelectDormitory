package com.pku.dormitory.utils;

/**
 * @author Yonmin
 * @date 2022/11/24 20:59
 */
public class TokenConstant {

    /**
     * access_token过期时间
     * 2小时过期
     * 2*60*60
     */
    public static final long ACCESS_EXPIRE = 720000;

    /**
     * refresh_token过期时间
     * 7天过期
     * 7*24*60*60
     */
    public static final long REFRESH_EXPIRE = 604800;

    /**
     * 密钥
     */
    public static final String SECRET = "yourOwnSecretKey";

    /**
     * access token字段名
     */
    public static final String ACCESS_TOKEN = "access-token";

    /**
     * refresh token字段名
     */
    public static final String REFRESH_TOKEN = "refresh-token";

}
