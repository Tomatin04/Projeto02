package com.api.api.Model.Commet;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record OneData(Long id, Long anew, String comment, String username, List<OneData> respostas) {

}
