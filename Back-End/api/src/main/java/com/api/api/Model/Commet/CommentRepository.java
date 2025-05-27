package com.api.api.Model.Commet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = """
            SELECT * FROM comments WHERE id_new = :id
                """, nativeQuery = true)
    ArrayList<Comment> findAllByANew(Long id);
}
