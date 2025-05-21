package com.api.api.Model.User;

public record InformationData(UsuarioDTO usuario, String messagem) {

    public InformationData(User user, String messagem) {
        this(new UsuarioDTO(user.getNome(), user.getEmail()), messagem);
    }

    public record UsuarioDTO(String nome, String email){};
}
