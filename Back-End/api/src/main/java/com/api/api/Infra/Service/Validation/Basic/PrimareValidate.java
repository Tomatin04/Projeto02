package com.api.api.Infra.Service.Validation.Basic;

import com.api.api.Model.User.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;

public interface PrimareValidate {
    void valid(InfoValidation data);
}

