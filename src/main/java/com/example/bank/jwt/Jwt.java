package com.example.bank.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.bank.domain.user.Role;
import com.example.bank.domain.user.User;

import java.time.Duration;
import java.util.Date;

public class Jwt {
    private final static String SECRET = "2309740832740837";
    public final static String PREFIX = "Bearer ";
    private static final long ACCESS_TOKEN_EXPIRATION_TIME = Duration.ofDays(1).toMillis();

    public static String create(LoginUser user) {
        return JWT.create().withSubject("토큰제목")
                .withExpiresAt(new Date(System.currentTimeMillis() + Jwt.ACCESS_TOKEN_EXPIRATION_TIME))
                .withClaim("id", user.getUser().getId())
                .withClaim("role", user.getUser().getRole().name())
                .sign(Algorithm.HMAC512(Jwt.SECRET));
    }

    public static LoginUser verify(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC512(SECRET)).build().verify(token);
        Long id = verify.getClaim("id").asLong();
        String role = verify.getClaim("role").asString();
        User user = User.builder().id(id).role(Role.valueOf(role)).build();
        return new LoginUser(user);
    }
}


