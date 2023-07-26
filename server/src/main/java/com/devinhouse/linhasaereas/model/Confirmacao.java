package com.devinhouse.linhasaereas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "CONFIRMACAO")
@AllArgsConstructor
@NoArgsConstructor
public class Confirmacao {

    @Id
    private String eticket;

    private String assento;

    private LocalDateTime dataHoraConfirmacao;

    private Boolean malasDespachadas;

    @OneToOne
    @JoinColumn(name = "cpf", referencedColumnName = "cpf")
    private Passageiro passageiro;

}
