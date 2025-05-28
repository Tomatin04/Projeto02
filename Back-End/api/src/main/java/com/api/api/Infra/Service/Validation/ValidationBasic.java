package com.api.api.Infra.Service.Validation;

import com.api.api.Infra.Service.UserUtil;
import com.api.api.Infra.Service.Validation.Basic.InfoValidation;
import com.api.api.Infra.Service.Validation.Basic.PrimareValidate;
import com.api.api.Model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class ValidationBasic {

    @Autowired
    private  UserUtil userUtil;

    @Autowired
    private  List<PrimareValidate> validadores;

    public  User basicValidation(String token, Long id, Object item){
        var user = userUtil.getUserByToken(token);
        System.out.println(user.getEmail());
        var data = new InfoValidation(user, id, item);
        validadores.forEach(v ->v.valid(data));
        return user;
    }
}
