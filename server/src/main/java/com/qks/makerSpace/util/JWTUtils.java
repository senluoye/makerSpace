package com.qks.makerSpace.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;
@Component
public class JWTUtils {

    // 生成密钥
    private static final String key = "0123456789_0123456789_0123456789";
    private static final SecretKey secretKey = new SecretKeySpec(key.getBytes(), SignatureAlgorithm.HS256.getJcaName());

    public String createToken(Map<String, Object> user){
        // 1. 生成 token
//        return Jwts.builder()  // 创建 JWT 对象
//                .setClaims(user)  // 放入用户参数
//                .setExpiration(new Date(System.currentTimeMillis() + 5*50*60*1000))  // 过期时间
//                .setIssuedAt(new Date(System.currentTimeMillis()))  // 当前时间
//                .signWith(secretKey)    // 设置安全密钥（生成签名所需的密钥和算法）
//                .compact();

        return Jwts.builder()  // 创建 JWT 对象
                .setClaims(user)  // 放入用户参数
                .setIssuedAt(new Date(System.currentTimeMillis()))  // 当前时间
                .signWith(secretKey)    // 设置安全密钥（生成签名所需的密钥和算法）
                .compact();
    }

    /**
     * 验证token
     * 这个方法后续得改一改
     * @param token
     * @return
     */
    public static Boolean verify(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

//            final Date exp = claims.getExpiration();
//            Date now = new Date(System.currentTimeMillis());
//            return now.before(exp);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Map<String, Object> parser(String token){
        // 3. 解析token
        return Jwts.parser()                // 创建解析对象
                .setSigningKey(secretKey)   // 设置安全密钥（生成签名所需的密钥和算法）
                .parseClaimsJws(token)      // 解析token
                .getBody();
    }

}