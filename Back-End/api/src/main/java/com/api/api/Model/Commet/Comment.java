package com.api.api.Model.Commet;

import com.api.api.Model.News.New;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Table(name = "comments")
@Entity(name = "Comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_new")
    private New aNew;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_origin")
    private Comment origin;

    @OneToMany(mappedBy = "origin", fetch = FetchType.LAZY)
    private List<Comment> respostas;

}
