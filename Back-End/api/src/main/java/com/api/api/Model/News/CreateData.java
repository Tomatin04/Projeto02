package com.api.api.Model.News;

public record CreateData(String titulo, String conteudo) {

    public CreateData(New anew){
        this(anew.getTitulo(), anew.getConteudo());
    }
}
