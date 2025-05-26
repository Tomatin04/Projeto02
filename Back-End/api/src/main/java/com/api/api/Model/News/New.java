package com.api.api.Model.News;

import com.api.api.Model.User.User;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "news")
@Entity(name = "New")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class New {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String conteudo;
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_creator")
    private User creator;

    public New (CreateData data){
        this.titulo = data.titulo();
        this.conteudo = data.conteudo();
    }

    public void atualizarInformacoes(UpdateData data, User creator){
        if(data.titulo() != null) this.titulo = data.titulo();
        if(data.conteudo() != null) this.conteudo = data.conteudo();
        this.creator = creator;
    }

    public void deleteNew(){
        isDeleted = true;
    }

}
