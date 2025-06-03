package com.api.api.Model.Commet;

import com.api.api.Model.News.New;
import com.api.api.Model.User.User;
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

    private String username;

    @OneToMany(mappedBy = "origin", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Comment> respostas;

    public Comment(CreateData data, String username){
        this.comment = data.comment();
        this.username = username;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
