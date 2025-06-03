package com.api.api.Model.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateData(
        @NotBlank
        String nome,
        @NotBlank @Email
        String email,
        @NotNull @Pattern(regexp = "\\d{7,80}")
        String senha
) {
}
