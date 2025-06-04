package com.api.api.Model.News;

import jakarta.validation.constraints.NotNull;

public record UpdateData(
        @NotNull
        Long id,
        String titulo,
        String conteudo) {
}
