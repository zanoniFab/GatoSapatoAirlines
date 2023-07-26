package com.devinhouse.linhasaereas.repository;

import com.devinhouse.linhasaereas.model.Confirmacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmacaoRepository extends JpaRepository <Confirmacao, String> {

    boolean existsByPassageiro_Cpf (Long cpf);

    Optional<Confirmacao> findByPassageiro_Cpf (Long cpf);

    boolean existsByAssento (String assento);

    Optional<Confirmacao> findConfirmacaoByEticket(String eticket);
}
