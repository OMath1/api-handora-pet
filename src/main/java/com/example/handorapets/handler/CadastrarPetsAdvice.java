package com.example.handorapets.handler;


import com.example.handorapets.dto.ErroDeFormularioResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@RestControllerAdvice
public class CadastrarPetsAdvice {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDeFormularioResponse> methodArgumentNotValidException(MethodArgumentNotValidException methodException) {
        List<ErroDeFormularioResponse> dto = new ArrayList<>();

        List<FieldError> fieldErrors = methodException.getBindingResult().getFieldErrors();
        fieldErrors
                .forEach(e -> {
                    String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
                    ErroDeFormularioResponse erro = new ErroDeFormularioResponse(e.getField(), mensagem);
                    dto.add(erro);
                });
        return dto;
    }

    @ExceptionHandler(InvalidFormatException.class)
    public List<String> invalidFormatException(InvalidFormatException exception) {
        String bindingResult = exception.getMessage();
        String tipo = "Algum campo ainda esta vazio :)";

        List<String> mensagens = new ArrayList<>();
        mensagens.add(bindingResult);
        mensagens.add(tipo);


        return mensagens;
    }
}
