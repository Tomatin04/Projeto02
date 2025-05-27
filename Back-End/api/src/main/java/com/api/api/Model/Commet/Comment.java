package com.api.api.Model.Commet;

import com.api.api.Model.News.New;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @JsonBackReference
    private Comment origin;

    @OneToMany(mappedBy = "origin", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Comment> respostas;

    public Comment(CreateData data){
        this.comment = data.comment();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
