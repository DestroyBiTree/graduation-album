package com.nwu.graduationalbum.util;


import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.nwu.graduationalbum.util.Constants.TOKEN_SIGN;
import static com.nwu.graduationalbum.util.Constants.TOKEN_TTL;


@Component
public class TokenUtil {

    /**
     * 根据用户Id生成token
     * @param userId
     * @return
     */
    public static String createToken(String userId) {
        String token = Jwts.builder().setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_TTL))
                .signWith(SignatureAlgorithm.HS512, TOKEN_SIGN).compressWith(CompressionCodecs.GZIP).compact();
        return token;
    }

    /**
     * 根据token获取用户名
     * @param token
     * @return
     */
    public static String getUserFromToken(String token) {
        String user = Jwts.parser().setSigningKey(TOKEN_SIGN).parseClaimsJws(token).getBody().getSubject();
        return user;
    }

}
