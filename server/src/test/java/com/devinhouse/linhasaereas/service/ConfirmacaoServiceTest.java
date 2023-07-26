package com.devinhouse.linhasaereas.service;

import com.devinhouse.linhasaereas.exception.MalaNaoDespachadaException;
import com.devinhouse.linhasaereas.exception.PassageiroMenorDeIdadeException;
import com.devinhouse.linhasaereas.exception.RegistroEncontradoException;
import com.devinhouse.linhasaereas.exception.RegistroNaoEncontradoException;
import com.devinhouse.linhasaereas.model.Confirmacao;
import com.devinhouse.linhasaereas.model.Passageiro;
import com.devinhouse.linhasaereas.repository.ConfirmacaoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ConfirmacaoServiceTest {

    @Mock
    private ConfirmacaoRepository confirmacaoRepository;

    @InjectMocks
    private ConfirmacaoService confirmacaoService;

    @Mock
    private PassageiroService passageiroService;

    @Test
    @DisplayName("Retorna null quando o cpf não é localizado ")
    void consultarPorCpfInxistente() {
        Long cpf = 22222222222L;
        Mockito.when(confirmacaoRepository.findByPassageiro_Cpf(Mockito.anyLong())).thenReturn(Optional.empty());
        assertNull(confirmacaoService.consultarPorCpf(cpf));
    }

    @Test
    @DisplayName("Retorna os dados de Confirmacao de um CPF específico ")
    void consultarPorCpf() {
        Long cpf = 22222222222L;
        Passageiro passageiro = Passageiro.builder().cpf(cpf).build();
        Confirmacao confirmacao = new Confirmacao(null, "4B",null,true,passageiro);
        Mockito.when(confirmacaoRepository.findByPassageiro_Cpf(cpf)).thenReturn(Optional.of(confirmacao));
        Confirmacao ConfirmacaoRealizado = confirmacaoService.consultarPorCpf(cpf);
        assertEquals(22222222222L,ConfirmacaoRealizado.getPassageiro().getCpf());
    }

    @Test
    @DisplayName("Retorna a lista dos assentos da aeronave ")
    void consultarAssentos() {
        List<String> assentos = confirmacaoService.consultarAssentos();
        assertEquals(54,assentos.size());
    }


    @Test
    @DisplayName("Retorna um erro quando a Confirmacao do passageiro já foi realizada ")
    void criar_ConfirmacaoRepetido() {
        Passageiro passageiro = Passageiro.builder().cpf(22222222222L).build();
        Confirmacao confirmacao = new Confirmacao(null, "4B",null,true,passageiro);
        Mockito.when(confirmacaoRepository.existsByPassageiro_Cpf(Mockito.anyLong())).thenReturn(true);
        Mockito.when(passageiroService.verificarMaioridade(Mockito.any())).thenReturn(true);
        assertThrows(RegistroEncontradoException.class, () -> confirmacaoService.criar(confirmacao));
    }

    @Test
    @DisplayName("Retorna um erro quando o assento está ocupado ")
    void criar_AssentoOcupado() {
        Passageiro passageiro = Passageiro.builder().cpf(22222222222L).build();
        Confirmacao confirmacao = new Confirmacao(null, "4B",null,true,passageiro);
        Mockito.when(confirmacaoRepository.existsByPassageiro_Cpf(Mockito.anyLong())).thenReturn(false);
        Mockito.when(confirmacaoRepository.existsByAssento(Mockito.anyString())).thenReturn(true);
        Mockito.when(passageiroService.verificarMaioridade(Mockito.any())).thenReturn(true);
        assertThrows(RegistroEncontradoException.class, () -> confirmacaoService.criar(confirmacao));
    }

    @Test
    @DisplayName("Retorna um erro quando o passageiro não existe ")
    void criar_PassageiroInexistente() {
        Passageiro passageiro = Passageiro.builder().cpf(2222222222L).build();
        Confirmacao confirmacao = new Confirmacao(null, "4B",null,true,passageiro);
        Mockito.when(confirmacaoRepository.existsByPassageiro_Cpf(Mockito.anyLong())).thenReturn(false);
        Mockito.when(confirmacaoRepository.existsByAssento(Mockito.anyString())).thenReturn(false);
        Mockito.when(passageiroService.existPassageiroByCpf(Mockito.any())).thenReturn(false);
        Mockito.when(passageiroService.verificarMaioridade(Mockito.any())).thenReturn(true);
        assertThrows(RegistroNaoEncontradoException.class, () -> confirmacaoService.criar(confirmacao));
    }

    @Test
    @DisplayName("Retorna um erro quando o assento não existe ")
    void criar_AssentoInexistente() {
        Passageiro passageiro = Passageiro.builder().cpf(22222222222L).build();
        Confirmacao confirmacao = new Confirmacao(null, "4Z",null,true,passageiro);
        Mockito.when(confirmacaoRepository.existsByPassageiro_Cpf(Mockito.anyLong())).thenReturn(false);
        Mockito.when(confirmacaoRepository.existsByAssento(Mockito.anyString())).thenReturn(false);
        Mockito.when(passageiroService.existPassageiroByCpf(Mockito.any())).thenReturn(true);
        Mockito.when(passageiroService.verificarMaioridade(Mockito.any())).thenReturn(true);
        assertThrows(RegistroNaoEncontradoException.class, () -> confirmacaoService.criar(confirmacao));
    }

    @Test
    @DisplayName("Retorna um erro quando o passageiro faz Confirmacao na fila da saída de emergência e a mala não foi despachado  ")
    void criar_MalaNaoDespachada() {
        Passageiro passageiro = Passageiro.builder().cpf(22222222222L).build();
        Confirmacao confirmacao = new Confirmacao(null, "4D",null,false,passageiro);
        assertThrows(MalaNaoDespachadaException.class, () -> confirmacaoService.criar(confirmacao));
    }

    @Test
    @DisplayName("Retorna um erro quando o passageiro menor de idade faz Confirmacao na fila da saída de emergência ")
    void criar_PassageiroMenorDeIdade() {
        Passageiro passageiro = Passageiro.builder().cpf(22222222222L).build();
        Confirmacao confirmacao = new Confirmacao(null, "4D",null,true,passageiro);
        Mockito.when(passageiroService.verificarMaioridade(Mockito.anyLong())).thenReturn(false);
        assertThrows(PassageiroMenorDeIdadeException.class, () -> confirmacaoService.criar(confirmacao));
    }

    @Test
    @DisplayName("Realiza a Confirmacao quando os dados de Confirmacao forem válidos ")
    void criar() {
        Passageiro passageiro = Passageiro.builder().cpf(2222222222L).build();
        Confirmacao confirmacao = new Confirmacao(null, "4D",null,true,passageiro);
        Mockito.when(confirmacaoRepository.existsByPassageiro_Cpf(Mockito.anyLong())).thenReturn(false);
        Mockito.when(confirmacaoRepository.existsByAssento(Mockito.anyString())).thenReturn(false);
        Mockito.when(passageiroService.existPassageiroByCpf(Mockito.any())).thenReturn(true);
        Mockito.when(passageiroService.verificarMaioridade(Mockito.anyLong())).thenReturn(true);
        confirmacaoService.criar(confirmacao);
        assertNotNull(confirmacao.getEticket());
        assertNotNull(confirmacao.getDataHoraConfirmacao());
    }

}
