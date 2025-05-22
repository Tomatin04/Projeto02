package com.api.api.Model.News;

public record ShowData(Long id, String titulo, String conteudo) {

    public ShowData(New anew){
        this(anew.getId(), anew.getTitulo(), anew.getConteudo());
    }
}
