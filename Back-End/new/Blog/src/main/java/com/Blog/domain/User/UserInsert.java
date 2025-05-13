package com.Blog.domain.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserInsert(
        @NotBlank @Email
        String username,
        @NotBlank
        String password,
        @NotBlank
        String firstname
) {
}
