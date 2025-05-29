package com.api.api.Infra.Service;

import com.api.api.Infra.Service.Validation.ValidationBasic;
import com.api.api.Model.Commet.Comment;
import com.api.api.Model.Commet.CommentRepository;
import com.api.api.Model.Commet.CreateData;
import com.api.api.Model.Commet.Teste;
import com.api.api.Model.News.NewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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


    public List<Teste> construirArvoreComentarios(Long id) {
        var comentarios = commentRepository.findAllByANew(id);
        // Agrupar por ID do comentário pai

        Map<Long, List<Comment>> respostasPorPai = comentarios.stream()
                .filter(c -> c.getOrigin() != null)
                .collect(Collectors.groupingBy(c -> c.getOrigin().getId()));

        // Filtrar comentários "raiz" (sem pai) e montar a árvore recursivamente
        return comentarios.stream()
                .filter(c -> c.getOrigin() == null)
                .map(c -> montarComentarioRecursivamente(c, respostasPorPai))
                .collect(Collectors.toList());
    }

    private Teste montarComentarioRecursivamente(Comment comentario, Map<Long, List<Comment>> respostasPorPai) {
        List<Teste> filhos = Optional.ofNullable(respostasPorPai.get(comentario.getId()))
                .orElse(List.of())
                .stream()
                .map(filho -> montarComentarioRecursivamente(filho, respostasPorPai))
                .collect(Collectors.toList());

        return new Teste(
                comentario.getId(),
                comentario.getANew().getId(),
                comentario.getComment(),
                filhos.isEmpty() ? null : filhos
        );
    }
}
