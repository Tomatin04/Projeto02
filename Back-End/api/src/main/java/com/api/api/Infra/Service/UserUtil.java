package com.api.api.Infra.Service;

import com.api.api.Infra.Securety.TokenService;
import com.api.api.Model.User.User;
import com.api.api.Model.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUtil {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    public User getUserByToken(String token){
        String clearToken = token.replace("Bearer ", "");
        var user = userRepository.findById(tokenService.getIdFromToken(clearToken)).orElseThrow();

        return user;

    }

    public Long getIdFromToken(String token){
        String clearToken = token.replace("Bearer ", "");
        Long id = tokenService.getIdFromToken(clearToken);
        return id;
    }

    public Boolean verifyUserDeleted(String email){
        return userRepository.userActive(email);
    }
}
