package com.api.api.Model.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UpdateData(
        String nome,
        @NotBlank
        @Pattern(regexp = "\\d{8,80}")
        String senha
) {
}
