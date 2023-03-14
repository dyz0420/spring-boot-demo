package com.dyzhome.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dyz
 */
public class JwtUtil {
    /**
     * JWT密钥
     */
    public static final String SECRET = "ASD!@#F^%A";
    /**
     * 过期时间（小时）
     */
    public static final int EXPIRES_TIME = 24;

    /**
     * 生成Token
     * @param userId 用户id
     * @return token
     */
    public static String createToken(Long userId) {
        Map<String, Object> header = new HashMap<>();
        // 过期时间
        Calendar expires = Calendar.getInstance();
        expires.add(Calendar.HOUR, EXPIRES_TIME);
        return JWT.create()
                // 第一部分Header
                .withHeader(header)
                // 第二部分Payload
                .withClaim("userId", userId)
                .withExpiresAt(expires.getTime())
                // 第三部分Signature
                .sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * token解析，过期抛出TokenExpiredException
     * @param token token
     * @return 返回用户id
     */
    public static Long parseToken(String token) {
        //创建解析对象，使用的算法和secret要和创建token时保持一致
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Claim claim = decodedJWT.getClaim("userId");
        return claim.asLong();
    }

}
