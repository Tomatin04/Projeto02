package com.api.api.Model.News;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateData(
        @NotNull
        String titulo,
        @NotNull
        String conteudo) {

    public CreateData(New anew){
        this(anew.getTitulo(), anew.getConteudo());
    }
}
