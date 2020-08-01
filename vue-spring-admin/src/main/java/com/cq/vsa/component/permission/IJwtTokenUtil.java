package com.cq.vsa.component.permission;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * json web token 工具接口
 * @Author: chengqi
 * @Date: Created in 13:34 2019/4/9
 */
public interface IJwtTokenUtil {

    /**
     * 从数据声明生成令牌
     * @param claims 数据声明
     * @return 令牌
     */
    String generateToken(Map<String, Object> claims);

    /**
     * 从令牌中获取数据声明
     * @param token 令牌
     * @return 数据声明
     */
    Claims getClaimsFromToken(String token);

    /**
     * 获取请求头key
     * @return
     */
    String getHeader();

    /**
     * 生成令牌
     * @param userDetails 用户实体
     * @return 令牌
     */
    default String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put(Claims.SUBJECT, userDetails.getUsername());
        claims.put(Claims.ISSUED_AT, new Date());
        return generateToken(claims);
    }

    /**
     * 从令牌中获取用户名
     * @param token 令牌
     * @return 用户名
     */
    default String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 判断令牌是否过期
     * @param token 令牌
     * @return 是否过期
     */
    default Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * 刷新令牌
     * @param token 原令牌
     * @return 新令牌
     */
    default String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(Claims.ISSUED_AT, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 验证令牌
     * @param token       令牌
     * @param userDetails 用户
     * @return 是否有效
     */
    default Boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
