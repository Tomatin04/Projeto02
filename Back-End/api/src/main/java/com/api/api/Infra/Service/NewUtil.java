package com.api.api.Infra.Service;

import com.api.api.Infra.Service.Validation.AdminCheck;
import com.api.api.Infra.Service.Validation.ValidationBasic;
import com.api.api.Model.News.CreateData;
import com.api.api.Model.News.New;
import com.api.api.Model.News.NewRepository;
import com.api.api.Model.News.UpdateData;
import com.api.api.Model.User.User;
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

    @Autowired
    private ValidationBasic validationBasic;

    public ResponseEntity saveUtil(String token, CreateData data, UriComponentsBuilder uriComponentsBuilder){
        var user = validationBasic.basicValidation(token, null, New.class);
        var anew = new New(data);
        anew.setCreator(user);
        newRepository.save(anew);
        var uri = uriComponentsBuilder.path("api/new/{id}").buildAndExpand(anew.getId()).toUri();
        return ResponseEntity.created(uri).body(new InformationMessage("Sucesso ao criar o noticia"));
    }

    public ResponseEntity updateUtil(String token, UpdateData data){
        var user = validationBasic.basicValidation(token, null, New.class);
        New anew = newRepository.getReferenceById(data.id());
        anew.atualizarInformacoes(data, user);
        return ResponseEntity.ok(new InformationMessage("Noticia atualziada com sucesso"));
    }

    public  ResponseEntity deleteUtil(String token, Long id){
        var user = validationBasic.basicValidation(token, null, New.class);
        return ResponseEntity.ok(new InformationMessage("Noticia excluida  com sucesso"));
    }
}
