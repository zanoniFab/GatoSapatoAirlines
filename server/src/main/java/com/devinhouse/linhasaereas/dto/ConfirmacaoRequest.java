package com.devinhouse.linhasaereas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConfirmacaoRequest {

    private Long cpf;
    private String assento;
    private boolean malasDespachadas;
}
