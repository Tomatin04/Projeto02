package com.projeto.blog.infra.securety;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.projeto.blog.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Set;

@Service
public class TokenService {

    @Value("${api.securety.token.secret}")
    private String secret;

    private final Set<String> invalidTokens = new HashSet<>();

    public String genToken(User user){
        try{
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Blog")
                    .withSubject(user.getEmail())
                    .withExpiresAt(encodeData())
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar o token: ", e);
        }
    }

    public String getSubject(String tokenJWT){
        if(invalidTokens.contains(tokenJWT)) throw new RuntimeException("Token invalido");
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("Blog")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token invalido");
        }
    }

    public void invalidateToken(String tokenJWT){
        invalidTokens.add(tokenJWT);
    }

    private Instant encodeData(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-3:00"));
    }
}
