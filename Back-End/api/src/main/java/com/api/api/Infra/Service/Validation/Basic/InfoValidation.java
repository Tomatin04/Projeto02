package com.api.api.Infra.Service.Validation.Basic;

import com.api.api.Model.User.User;
import jakarta.validation.constraints.NotNull;

public record InfoValidation(
        @NotNull
        User user,
        Long id,
        @NotNull
        Object item
) {
}
