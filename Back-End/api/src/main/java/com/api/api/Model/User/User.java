package com.api.api.Model.User;

import com.api.api.Infra.Securety.SecuretyConfiguration;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;
    private boolean ativo;

    public User(CreateData data){
        this.nome = data.nome();
        this.email = data.email();
        this.senha = new SecuretyConfiguration().passwordEncoder().encode(data.senha());
        this.ativo = true;
    }

    public void atualizarInformacoes(UpdateData data){
        if(data.nome() != null){ this.nome = data.nome();}
        if(data.senha() != null && !data.senha().isEmpty()){this.senha =new SecuretyConfiguration().passwordEncoder().encode(data.senha());}}


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
