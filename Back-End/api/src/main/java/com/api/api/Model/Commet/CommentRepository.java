package com.api.api.Model.Commet;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    ArrayList<Comment> findAllByANew(Long id);
}
