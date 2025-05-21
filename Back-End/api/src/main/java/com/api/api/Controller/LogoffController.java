package com.api.api.Controller;


import com.api.api.Infra.Securety.BlackList;
import com.api.api.Infra.Service.InformationMessage;
import com.api.api.Model.User.InformationData;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logout")
@SecurityRequirement(name = "bearer-key")
public class LogoffController {

    @Autowired
    private BlackList blackList;

    @PostMapping
    public ResponseEntity logout(@RequestHeader("Authorization") String token){
        String clearToken = token.replace("Bearer ", "");
        blackList.blacklistToken(clearToken);
        return ResponseEntity.ok(new InformationMessage( "Logout realizado com suscesso"));
    }
}
