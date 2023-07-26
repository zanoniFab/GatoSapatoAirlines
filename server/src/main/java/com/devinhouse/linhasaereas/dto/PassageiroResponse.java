package com.devinhouse.linhasaereas.dto;

import com.devinhouse.linhasaereas.model.Classificacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PassageiroResponse {

    private Long cpf;

    private String nome;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private Classificacao classificacao;

    private Integer milhas;

    private String eticket;

    private String assento;


}