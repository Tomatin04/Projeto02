package com.api.api.Model.Commet;

import com.api.api.Model.News.New;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Teste(Long id, Long anew, String comment, List<Teste> respostas) {

}
