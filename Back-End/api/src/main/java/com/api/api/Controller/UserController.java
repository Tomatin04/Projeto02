package com.api.api.Controller;

import com.api.api.Infra.Securety.SecuretyConfiguration;
import com.api.api.Infra.Securety.TokenService;
import com.api.api.Infra.Service.InformationMessage;
import com.api.api.Model.User.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CreateData data, UriComponentsBuilder uriComponentsBuilder){
        var user = new User(data);
        repository.save(user);
        var uri = uriComponentsBuilder.path("api/usuarios/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new InformationMessage("Sucesso ao criar o usuário"));
    }

    @GetMapping
    public ResponseEntity show (@RequestHeader("Authorization")  String token){
        String clearToken = token.replace("Bearer ", "");
        var user = repository.findById(tokenService.getIdFromToken(clearToken)).orElseThrow();
        return ResponseEntity.ok(new InformationData(user, "Sucesso ao buscar usuario"));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update (@RequestHeader("Authorization") String token, @RequestBody @Valid UpdateData data, UriComponentsBuilder uriComponentsBuilder){
        String clearToken = token.replace("Bearer ", "");
        var user = repository.findById(tokenService.getIdFromToken(clearToken)).orElseThrow();
        user.atualizarInformacoes(data);
        return ResponseEntity.ok(new InformationData(user, "Sucesso ao salvar o usuario"));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity delete (@RequestHeader("Authorization")  String token){
        String clearToken = token.replace("Bearer ", "");
        repository.intelDeleteUser(tokenService.getIdFromToken(clearToken));
        return ResponseEntity.ok().build();
    }
}
