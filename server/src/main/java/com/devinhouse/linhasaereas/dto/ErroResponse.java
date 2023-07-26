package com.devinhouse.linhasaereas.dto;

import lombok.Data;

@Data
public class ErroResponse {

    private String mensagem;

    public ErroResponse(String mensagem) {
        this.mensagem = mensagem;
    }

}
