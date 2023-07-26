package com.devinhouse.linhasaereas.dto;

import com.devinhouse.linhasaereas.model.Classificacao;
import com.devinhouse.linhasaereas.model.Passageiro;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConfirmacaoCompletoResponse {

    private String eticket;

    private Long cpf;

    private String nome;

    private String assento;

    private Boolean malasDespachadas;

    private LocalDateTime dataHoraConfirmacao;

    private Classificacao classificacao;

}
