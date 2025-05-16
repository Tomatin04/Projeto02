package com.Blog.infra.securety;

import com.Blog.domain.User.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class TokenService {

    @Value("${api.securety.token.secret}")
    private String secret;



    public String tokenGeneration(User user){
        try{
            var alg = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Blog API")
                    .withSubject(user.getUsername())
                    .withExpiresAt(timeExpire())
                    .sign(alg);
        }catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar token de acesso, ERRO: ", e);
        }
    }

    public String getSubject(String tokenJWT){
        try {
            var alg = Algorithm.HMAC256(secret);
            return JWT.require(alg)
                    .withIssuer("Blog API")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        }catch (JWTVerificationException e){
            throw  new RuntimeException("Token invalido, ERRO: ", e);
        }
    }



    private Instant timeExpire(){return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));}
}
