package com.api.api.Controller;

import com.api.api.Infra.Securety.TokenJWTData;
import com.api.api.Infra.Securety.TokenService;
import com.api.api.Infra.Service.InformationMessage;
import com.api.api.Infra.Service.UserUtil;
import com.api.api.Model.User.AuthenticationData;
import com.api.api.Model.User.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserUtil userUtil;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationData data){
        if(!userUtil.verifyUserDeleted(data.email())) return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new InformationMessage("Impossivel Logar"));
        var authToken = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = authenticationManager.authenticate(authToken);
        var tokenJWT = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new TokenJWTData(tokenJWT));
    }
}
