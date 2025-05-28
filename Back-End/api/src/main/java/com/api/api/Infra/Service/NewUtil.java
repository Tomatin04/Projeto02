package com.api.api.Infra.Service;

import com.api.api.Infra.Service.Validation.AdminCheck;
import com.api.api.Model.News.CreateData;
import com.api.api.Model.News.New;
import com.api.api.Model.News.NewRepository;
import com.api.api.Model.News.UpdateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class NewUtil {

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private NewRepository newRepository;

    public ResponseEntity saveUtil(String token, CreateData data, UriComponentsBuilder uriComponentsBuilder){
        var user = userUtil.getUserByToken(token);
        if(AdminCheck.checkIsAdmin(user)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new InformationMessage("Usuário não autorizado a salvar noticias"));
        };
        var anew = new New(data);
        anew.setCreator(user);
        newRepository.save(anew);
        var uri = uriComponentsBuilder.path("api/new/{id}").buildAndExpand(anew.getId()).toUri();
        return ResponseEntity.created(uri).body(new InformationMessage("Sucesso ao criar o noticia"));
    }

    public ResponseEntity updateUtil(String token, UpdateData data){

    }


    private ResponseEntity  tokenValidation(String token){
        var user = userUtil.getUserByToken(token);
        if(AdminCheck.checkIsAdmin(user)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new InformationMessage("Usuário não autorizado a salvar noticias"));
        };
        return null;
    }
}
