package com.devinhouse.linhasaereas.service;

import com.devinhouse.linhasaereas.exception.*;
import com.devinhouse.linhasaereas.model.Confirmacao;
import com.devinhouse.linhasaereas.model.Passageiro;
import com.devinhouse.linhasaereas.repository.ConfirmacaoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ConfirmacaoService {

    private ConfirmacaoRepository repository;

    private PassageiroService passageiroService;

    public Confirmacao consultarPorCpf(Long cpf) {
        Optional<Confirmacao> confirmacaoOptional = repository.findByPassageiro_Cpf(cpf);
        if(confirmacaoOptional.isEmpty())
            return null;
        Confirmacao confirmacao = confirmacaoOptional.get();
        return confirmacao;
    }

    public Confirmacao buscarPorEticket(String eticket) {
        return repository.findConfirmacaoByEticket(eticket).orElseThrow(EticketNaoCadastroException::new);
    }

    public List<String> consultarAssentos(){
        List<String> assentos = new ArrayList<>();
        int totalFileiras = 9;
        for (int i = 1; i <= totalFileiras; i++) {
            for (char j = 'A'; j <= 'F'; j++) {
                String assento = i + String.valueOf(j);
                assentos.add(assento);
            }
        }
        return assentos;
    }

    public Confirmacao criar(Confirmacao confirmacao) {
        Long cpf = confirmacao.getPassageiro().getCpf();
        String assento = confirmacao.getAssento();
        Boolean isAssento = consultarAssentos().contains(assento);
        if ((assento.contains("4")||assento.contains("5")) && !confirmacao.getMalasDespachadas()) {
            throw new MalaNaoDespachadaException(assento);
        } else if ((assento.contains("4")||assento.contains("5")) && !passageiroService.verificarMaioridade(cpf)) {
            throw new PassageiroMenorDeIdadeException(cpf);
        } else if (repository.existsByPassageiro_Cpf(cpf)) {
            throw new RegistroEncontradoException(cpf);
        } else if (repository.existsByAssento(assento)) {
            throw new RegistroEncontradoException(assento);
        } else if (!passageiroService.existPassageiroByCpf(cpf)) {
            throw new RegistroNaoEncontradoException(cpf);
        } else if (!isAssento) {
            throw new RegistroNaoEncontradoException(assento);
        }

        String eticket = UUID.randomUUID().toString();
        confirmacao.setEticket(eticket);
        confirmacao.setDataHoraConfirmacao(LocalDateTime.now());
        passageiroService.acrescentarPontos(cpf);
        repository.save(confirmacao);
        log.info("Confirmação feita pelo passageiro de CPF {} com e-ticket {}", cpf, eticket);
        return confirmacao;
    }

    public Boolean verificarDisponibilidade(String assento) {
        return repository.existsByAssento(assento);
    }
}
