package com.cq.vsa.component.permission;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**json web token 工具默认实现
 * @Author: chengqi
 * @Date: Created in 13:45 2019/4/9
 */
@Component
@ConfigurationProperties(prefix = "jwt")
public class DefaultJwtTokenUtil implements IJwtTokenUtil {

    private String secret;

    /**
     * 过期时间 毫秒
     */
    private long expiration;

    private String header;

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + expiration);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).
                signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    @Override
    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    @Override
    public String getHeader() {
        return header;
    }
}
