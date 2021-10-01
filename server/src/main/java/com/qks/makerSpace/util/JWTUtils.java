package com.qks.makerSpace.util;

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
    public String createToken(Map<String, Object> user){
        return Jwts.builder()                                                        // 创建 JWT 对象
                .setClaims(user)                                                     // 放入用户参数
                .setExpiration(new Date(System.currentTimeMillis() + 24*60*60*1000)) // 过期时间
                .setIssuedAt(new Date(System.currentTimeMillis()))                   // 当前时间
                .signWith(secretKey)                                                 // 设置安全密钥（生成签名所需的密钥和算法）
                .compact();

    }

    /**
     * 验证token
     * @param token
     * @return
     */
    public static Boolean verify(String token) {
        Map<String, Object> map = parser(token);
//        System.out.println(map);
        return Objects.equals(map.get("name").toString(), "admin")
                && Objects.equals(map.get("password").toString(), "123456");
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public static Map<String, Object> parser(String token){
        // 3. 解析token
        return Jwts.parser()                // 创建解析对象
                .setSigningKey(secretKey)   // 设置安全密钥（生成签名所需的密钥和算法）
                .parseClaimsJws(token)      // 解析token
                .getBody();
    }

}