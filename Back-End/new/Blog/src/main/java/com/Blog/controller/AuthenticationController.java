package com.Blog.controller;

import com.Blog.domain.User.AuthenticationData;
import com.Blog.domain.User.User;
import com.Blog.domain.User.UserRepository;
import com.Blog.infra.securety.DataJWTToken;
import com.Blog.infra.securety.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticacao")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authenticate(@RequestBody @Valid AuthenticationData data) {
        var authToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authentication = manager.authenticate(authToken);

        var tokenJWT = tokenService.tokenGeneration((User) authentication.getPrincipal());
        return ResponseEntity.ok(new DataJWTToken(tokenJWT));
    }
}
