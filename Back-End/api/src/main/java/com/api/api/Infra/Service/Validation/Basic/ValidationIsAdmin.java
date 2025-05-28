package com.api.api.Infra.Service.Validation.Basic;
import com.api.api.Infra.Exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ValidationIsAdmin implements PrimareValidate{

    @Value("${api.valid.mail.admin}")
    private  String mailAdmin;

    @Override
    public void valid(InfoValidation data) {
        if(!data.user().getEmail().equals(mailAdmin)) {
            throw new ValidacaoException("ERROR");
        };
    }
}
