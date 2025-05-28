package com.api.api.Infra.Service.Validation.Basic;

import com.api.api.Infra.Exceptions.ValidacaoException;
import com.api.api.Model.Commet.Comment;
import com.api.api.Model.Commet.CommentRepository;
import com.api.api.Model.News.New;
import com.api.api.Model.News.NewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationIsExist implements PrimareValidate{

    @Autowired
    private NewRepository newRepository;

    @Autowired
    private CommentRepository cRepository;

    @Override
    public void valid(InfoValidation data) {
        if(data.id() != null){
            if(data.item() instanceof New){
                if(newRepository.findById(data.id()).isEmpty()){
                    throw new ValidacaoException("ERROR");
                }
            }else if(data.item() instanceof Comment){
                if(cRepository.findById(data.id()).isEmpty()){
                    throw new ValidacaoException("ERROR");
                }
            }else{
                throw new ValidacaoException("ERROR");
            }
        }

    }
}
