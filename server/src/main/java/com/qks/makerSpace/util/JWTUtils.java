package com.qks.makerSpace.util;

import com.qks.makerSpace.exception.LoginException;
import com.qks.makerSpace.exception.ServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Component
public class JWTUtils {

    // 生成密钥
    private static final String key = "0123456789_0123456789_0123456789";
    private static final SecretKey secretKey = new SecretKeySpec(key.getBytes(), SignatureAlgorithm.HS256.getJcaName());

    /**
     * 生成token
     * @param user
     * @return
     */
    public static String createToken(Map<String, Object> user){
        return Jwts.builder()
                .setClaims(user)
                .setExpiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(secretKey)
                .compact();
    }

    /**
     * 验证token
     * @param token
     * @return
     */
    public static Boolean verify(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
        } catch (JwtException e) {
            return false;
        }

        return true;
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public static Claims parser(String token) throws ServiceException {
        if (verify(token)) {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } else {
            throw new ServiceException("登陆信息过期或未登录");
        }
    }

}