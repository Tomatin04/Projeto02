package com.api.api.Infra.Service;

import com.api.api.Model.User.User;
import jakarta.validation.constraints.Email;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AdminCheck {

    @Value("${api.valid.mail.admin}")
    @Email
    private String mailAdmin;


    public boolean checkIsAdmin(User user){
        if(user.getEmail().equals(mailAdmin)) return true;
        return false;
    }


    public ResponseEntity forbind(String menssagem){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new InformationMessage(menssagem));
    }
}
