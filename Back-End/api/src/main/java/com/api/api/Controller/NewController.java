package com.api.api.Controller;

import com.api.api.Infra.Service.NewUtil;
import com.api.api.Infra.Service.Validation.AdminCheck;
import com.api.api.Infra.Service.InformationMessage;
import com.api.api.Infra.Service.UserUtil;
import com.api.api.Model.News.*;
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
        return ResponseEntity.ok(new ShowData(anew));
    }

    /*
    @GetMapping("/{titulo}")
    public ResponseEntity findByTitle(@PathVariable String titulo){
        var anew = repository.findByTituloNotDeleted(titulo);
        return ResponseEntity.ok(anew);
    }
    */

    @PostMapping
    @Transactional
    public ResponseEntity create (@RequestHeader("Authorization")  String token, @RequestBody @Valid CreateData data, UriComponentsBuilder uriComponentsBuilder){
        return newUtil.saveUtil(token, data, uriComponentsBuilder);
    }


    @PutMapping
    @Transactional
    public ResponseEntity update (@RequestHeader("Authorization")  String token, @RequestBody @Valid UpdateData data, UriComponentsBuilder uriComponentsBuilder){
        return newUtil.updateUtil(token, data);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity delete (@RequestBody @Valid DeleteData data, @RequestHeader("Authorization")  String token){
        return newUtil.deleteUtil(token, data.id());
    }

}
