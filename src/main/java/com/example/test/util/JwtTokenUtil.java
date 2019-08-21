package com.example.test.util;

import com.example.test.constant.Constants;
import com.example.test.jwt.JWTInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class JwtTokenUtil {

    private static final String SECRET = "jwtsecretdemo";

    /**
     * 生成token
     *
     * @param jwtInfo
     * @return
     */
    public String generateToken(JWTInfo jwtInfo) {
        return Jwts.builder().setSubject(jwtInfo.getUsername())
                .claim(Constants.JWT_KEY_USER_ID, jwtInfo.getUserId())
                .claim(Constants.JWT_KEY_NAME, jwtInfo.getUsername())
                .claim(Constants.JWT_KEY_EXTENDS, jwtInfo.getExtend())
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public Jws<Claims> parseToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
    }

    /**
     * 从token 获取用户信息
     *
     * @param token
     * @return
     */
    public JWTInfo getInfoFromToken(String token) {
        Claims body = parseToken(token).getBody();
        JWTInfo jwtInfo = new JWTInfo();
        jwtInfo.setUserId(StringHelper.getObjectValue(body.get(Constants.JWT_KEY_USER_ID)));
        jwtInfo.setUsername(StringHelper.getObjectValue(body.get(Constants.JWT_KEY_NAME)));
        jwtInfo.setExtend(StringHelper.getObjectValue(body.get(Constants.JWT_KEY_EXTENDS)));
        return jwtInfo;
    }
}
