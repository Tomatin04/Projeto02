package com.api.api.Infra.Service;

import com.api.api.Model.Commet.Comment;
import com.api.api.Model.Commet.CommentRepository;
import com.api.api.Model.Commet.CreateData;
import com.api.api.Model.News.NewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentUtil {

    @Autowired
    private NewRepository newRepository;

    @Autowired
    private CommentRepository commentRepository;

    public Comment transformDataToComment(CreateData data){
        var comment = new Comment(data);
        comment.setANew(newRepository.getReferenceById(data.aNew()));
        if(data.origin()!=null)comment.setOrigin(commentRepository.getReferenceById(data.origin()));
        return comment;
    }

    public List<Comment> getAllCommentsFromNew(Long id){
        var comments = commentRepository.findAllByANew(id);

        List<Comment> commentsList = comments.stream().map(comment -> {
            Comment c = new Comment();
            c.setId(comment.getId());
            c.setANew(comment.getANew());
            c.setOrigin(comment.getOrigin());
            return c;
        }).toList();

        return commentsList;
    }
}
