package com.devinhouse.linhasaereas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="PASSAGEIROS")
@Builder
public class Passageiro {

    @Id
    private Long cpf;

    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataDeNascimento;

    @Enumerated(EnumType.STRING)
    private Classificacao classificacao;

    private Integer milhas;

}
