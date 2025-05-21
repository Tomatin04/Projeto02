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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_creator")
    private User creator;

}
