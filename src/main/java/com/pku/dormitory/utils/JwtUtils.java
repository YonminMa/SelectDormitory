package com.pku.dormitory.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {
    //7天过期
    private static final long expire = 604800; // 7*24*60*60
    //32位秘钥
    private static final String secret = "yourOwnSecretKey";

    //生成token
    public static String generateToken(String username){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * expire);
        return Jwts.builder()
                .setHeaderParam("type","JWT")
                .setSubject(username)
                .setIssuedAt(now) // 签发时间
                .setExpiration(expiration) // 过期时间
                .signWith(SignatureAlgorithm.HS512, secret) // 签发算法
                .compact();
    }

    //解析token
    public static Claims getClaimsByToken(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
