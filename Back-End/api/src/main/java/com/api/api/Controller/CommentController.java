package com.api.api.Controller;

import com.api.api.Infra.Service.CommentUtil;
import com.api.api.Infra.Service.InformationMessage;
import com.api.api.Model.Commet.Comment;
import com.api.api.Model.Commet.CommentRepository;
import com.api.api.Model.Commet.CreateData;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@SecurityRequirement(name = "bearer-key")
public class CommentController {

    @Autowired
    private CommentUtil commentUtil;

    @Autowired
    private CommentRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestHeader("Authorization")  String token, @RequestBody @Valid CreateData data){
        var comment = commentUtil.transformDataToComment(data);
        repository.save(comment);
        return ResponseEntity.ok(new InformationMessage("Comentario salvo com sucesso"));
    }

    @GetMapping("/{id}")
    public ResponseEntity showAllByNew(@PathVariable Long id){

        return null;
    }
}
