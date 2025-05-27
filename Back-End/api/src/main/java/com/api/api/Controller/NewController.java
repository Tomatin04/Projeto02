package com.api.api.Controller;

import com.api.api.Infra.Service.NewUtil;
import com.api.api.Infra.Service.Validation.AdminCheck;
import com.api.api.Infra.Service.InformationMessage;
import com.api.api.Infra.Service.UserUtil;
import com.api.api.Model.News.CreateData;
import com.api.api.Model.News.New;
import com.api.api.Model.News.NewRepository;
import com.api.api.Model.News.ShowData;
import com.api.api.Model.News.UpdateData;
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
    private NewUtil newUtil;

    @Autowired
    private AdminCheck admC;

    @GetMapping
    public ResponseEntity show (@PageableDefault(sort= {"titulo"}, size = 15) Pageable paginacao){
        var page = repository.findAllByIsNotDeleted(paginacao).map(ShowData::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        var anew = repository.findByIdNotDeleted(id);
        return ResponseEntity.ok(anew);
    }

    @GetMapping("/{titulo}")
    public ResponseEntity findByTitle(@PathVariable String titulo){
        var anew = repository.findByTituloNotDeleted(titulo);
        return ResponseEntity.ok(anew);
    }

    @PostMapping
    @Transactional
    public ResponseEntity create (@RequestHeader("Authorization")  String token, @RequestBody @Valid CreateData data, UriComponentsBuilder uriComponentsBuilder){
        return newUtil.saveUtil(token, data, uriComponentsBuilder);
    }

    /*
    @PutMapping
    @Transactional
    public ResponseEntity update (@RequestHeader("Authorization")  String token, @RequestBody @Valid UpdateData data, UriComponentsBuilder uriComponentsBuilder){
        var user = userUtil.getUserByToken(token);
        if(!admC.checkIsAdmin(user)) return admC.forbind("Usuário não autrizado a atualizar avisos");
        New anew = repository.getReferenceById(data.id());
        anew.atualizarInformacoes(data, user);
        return ResponseEntity.ok(new InformationMessage("Noticia atualziada com sucesso"));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete (@PathVariable Long id, @RequestHeader("Authorization")  String token){
        if(!admC.checkIsAdmin(userUtil.getUserByToken(token))) return admC.forbind("Usuário não autrizado a deletar avisos");
        New anew = repository.getReferenceById(id);
        anew.deleteNew();
        return ResponseEntity.ok(new InformationMessage("Noticia excluida com sucesso"));
    }
    */
}
