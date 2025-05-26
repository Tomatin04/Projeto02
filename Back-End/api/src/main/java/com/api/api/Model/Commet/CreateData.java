package com.api.api.Model.Commet;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateData(
        @NotBlank
        @NotNull
        @JsonAlias("id_new")
        Long aNew,
        @NotBlank
        @NotNull
        String comment,
        @NotBlank
        @JsonAlias("id_origin")
        Long origin
) {
}
