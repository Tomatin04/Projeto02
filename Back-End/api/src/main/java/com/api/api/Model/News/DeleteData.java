package com.api.api.Model.News;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DeleteData(
        @NotNull
        Long id
) {
}
