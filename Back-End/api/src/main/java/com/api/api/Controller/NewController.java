package com.api.api.Controller;

import com.api.api.Infra.Securety.TokenService;
import com.api.api.Infra.Service.InformationMessage;
import com.api.api.Infra.Service.UserUtil;
import com.api.api.Model.News.CreateData;
import com.api.api.Model.News.New;
import com.api.api.Model.News.NewRepository;
import com.api.api.Model.News.ShowData;
import com.api.api.Model.News.UpdateData;
import com.api.api.Model.User.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/new")
@SecurityRequirement(name = "bearer-key")
public class NewController {

    @Autowired
    private NewRepository repository;

    @Autowired
    private UserUtil userUtil;



    @GetMapping
    public ResponseEntity show (@PageableDefault(sort= {"titulo"}, size = 5) Pageable paginacao){
        var page = repository.findAll(paginacao).map(ShowData::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity create (@RequestHeader("Authorization")  String token, @RequestBody @Valid CreateData data, UriComponentsBuilder uriComponentsBuilder){
        var user = userUtil.getUserByToken(token);
        var anew = new New(data);
        anew.setCreator(user);
        repository.save(anew);
        var uri = uriComponentsBuilder.path("api/new/{id}").buildAndExpand(anew.getId()).toUri();
        return ResponseEntity.created(uri).body(new InformationMessage("Sucesso ao criar o noticia"));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update (@RequestHeader("Authorization")  String token, @RequestBody @Valid UpdateData data, UriComponentsBuilder uriComponentsBuilder){
        var user = userUtil.getUserByToken(token);
        var anew = repository.findById(data.id());
        return null;
    }

}
