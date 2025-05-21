package com.api.api.Controller;

import com.api.api.Model.News.NewRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/new")
@SecurityRequirement(name = "bearer-key")
public class NewController {

    @Autowired
    private NewRepository repository;

    @GetMapping
    public ResponseEntity show (){
        
    }
}
