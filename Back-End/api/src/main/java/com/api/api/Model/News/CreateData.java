package com.api.api.Model.News;

import jakarta.validation.constraints.NotBlank;

public record CreateData(
        @NotBlank
        String titulo,
        @NotBlank
        String conteudo) {

    public CreateData(New anew){
        this(anew.getTitulo(), anew.getConteudo());
    }
}
