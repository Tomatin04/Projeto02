package com.api.api.Infra.Securety;

import com.api.api.Model.User.User;
import com.api.api.Model.User.UserRepository;
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

@Service
public class TokenService {

    @Value("${api.securety.token.secret}")
    private String secret;

    @Autowired
    private UserRepository userRepository;

    public String generateToken(User user){
        try {
            var alg = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Email")
                    .withSubject(user.getEmail())
                    .withClaim("id", userRepository.userId(user.getEmail()))
                    .withExpiresAt(ExpireDate())
                    .sign(alg);
        }catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar o token: ", e);
        }
    }

    public String getSubject(String tokenJWT){
        try{
            var alg = Algorithm.HMAC256(secret);
            return JWT.require(alg)
                    .withIssuer("Email")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        }catch(JWTVerificationException e){
            throw new RuntimeException("Token invalido");
        }
    }

    public Long getIdFromToken(String tokenJWT){
        try{
            return JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer("Email")
                    .build()
                    .verify(tokenJWT)
                    .getClaim("id")
                    .asLong();
        }catch(JWTVerificationException e){
            throw new RuntimeException("ID não encontrado");
        }
    }


    private Instant ExpireDate(){return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));}
}
