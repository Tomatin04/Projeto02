package com.api.api.Infra.Service.Validation;

import com.api.api.Model.User.User;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AdminCheck {

    @Value("${api.valid.mail.admin}")
    @Email
    private static String mailAdmin;


    public static boolean checkIsAdmin(User user){
        if(user.getEmail().equals(mailAdmin)) return true;
        return false;
    }
}
