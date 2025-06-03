package com.api.api.Model.Commet;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateData(
        @NotNull
        @JsonAlias("id_new")
        Long aNew,
        @NotBlank
        @NotNull
        String comment,
        @JsonAlias("id_origin")
        Long origin
) {
}
