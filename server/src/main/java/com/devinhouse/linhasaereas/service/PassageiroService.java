package com.devinhouse.linhasaereas.service;

import com.devinhouse.linhasaereas.exception.RegistroNaoEncontradoException;
import com.devinhouse.linhasaereas.model.Confirmacao;
import com.devinhouse.linhasaereas.model.Passageiro;
import com.devinhouse.linhasaereas.repository.ConfirmacaoRepository;
import com.devinhouse.linhasaereas.repository.PassageiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassageiroService {

    @Autowired
    private PassageiroRepository repository;

    @Autowired
    private ConfirmacaoRepository confirmacaoRepository;


    public List<Passageiro> consultar() {
        List<Passageiro> passageiros = repository.findAll();
        return  passageiros;
    }

    public boolean existPassageiroByCpf (Long cpf){
        return repository.existsById(cpf);
    }

    public void acrescentarPontos(Long cpf) {
        Passageiro passageiro = consultarPorCpf(cpf);
        Integer milhas = passageiro.getMilhas();
        Integer milhasAdicionais = 0;
        switch(passageiro.getClassificacao()) {
            case VIP:
                milhasAdicionais = 100;
                break;
            case OURO:
                milhasAdicionais = 80;
                break;
            case PRATA:
                milhasAdicionais = 50;
                break;
            case BRONZE:
                milhasAdicionais = 30;
                break;
            case ASSOCIADO:
                milhasAdicionais = 10;
                break;
        }
        passageiro.setMilhas(milhas+milhasAdicionais);
        repository.save(passageiro);
    }

    public Passageiro consultarPorCpf(Long cpf) {
        Optional<Passageiro> passageiroOptional = repository.findById(cpf);
        if(passageiroOptional.isEmpty())
            throw new RegistroNaoEncontradoException(cpf);
        Passageiro passageiro = passageiroOptional.get();
        return passageiro;
    }

    public Boolean verificarMaioridade (Long cpf){
        Passageiro passageiro = consultarPorCpf(cpf);
        LocalDate hoje = LocalDate.now();
        LocalDate nascimento = passageiro.getDataDeNascimento();
        Period periodo = Period.between(nascimento, hoje);
        return periodo.getYears()>18;
    }

    public List<Passageiro> buscarPassageirosPendentes() {
        List<Passageiro> todosPassageiros = repository.findAll();
        List<Confirmacao> todasConfirmacoes = confirmacaoRepository.findAll();
        List<Passageiro> passageiroPendentes = new ArrayList<>();

//        List<Passageiro> passageirosPendentes2 = todosPassageiros.stream().filter(passageiro -> {
//            boolean temConfirmacao = todasConfirmacoes.stream()
//                    .anyMatch(confirmacao -> confirmacao.getPassageiro().getCpf().equals(passageiro.getCpf()));
//            return !temConfirmacao;
//        }).toList();

        for (Passageiro passageiro: todosPassageiros){
            boolean isPendente = true;
            for(Confirmacao confirmacao: todasConfirmacoes) {
                if(confirmacao.getPassageiro().getCpf().equals(passageiro.getCpf())) {
                    isPendente=false;
                    break;
                }
            }
            if(isPendente){
                passageiroPendentes.add(passageiro);
            }
        }

        return passageiroPendentes;
    }
}
