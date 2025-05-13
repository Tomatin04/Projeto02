package com.Blog.domain.User;

import jakarta.validation.constraints.NotNull;

public record UserUpdate(
        @NotNull
        Long id,
        @NotNull
        String firstname
) {
}
