package com.example.handorapets.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErroDeFormularioResponse {
    private String campo;
    private String erro;
}