package com.pku.dormitory.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yonmin
 * @date 2022/11/24 16:26
 */
public class JwtUtils {

    /**
     * 生成token
     *
     * @param username 用户名
     * @return token
     */
    public static String generateToken(String username, long EXPIRE) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * EXPIRE);
        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setSubject(username)
                // 签发时间
                .setIssuedAt(now)
                // 过期时间
                .setExpiration(expiration)
                // 签发算法
                .signWith(SignatureAlgorithm.HS512, TokenConstant.SECRET)
                .compact();
    }

    /**
     * 解析token
     *
     * @param token token
     * @return Claims对象
     */
    public static Claims getClaimsByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(TokenConstant.SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getToken(HttpServletRequest request) throws Exception {
        Map<String, String> mapHeader = new HashMap<>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement().toString();
            String value = request.getHeader(key);
            mapHeader.put(key, value);
        }

        return mapHeader.get(TokenConstant.ACCESS_TOKEN).toString();
    }
}
