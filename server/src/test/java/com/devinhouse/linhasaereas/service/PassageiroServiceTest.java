package com.devinhouse.linhasaereas.service;

import com.devinhouse.linhasaereas.exception.RegistroNaoEncontradoException;
import com.devinhouse.linhasaereas.model.Classificacao;
import com.devinhouse.linhasaereas.model.Confirmacao;
import com.devinhouse.linhasaereas.model.Passageiro;
import com.devinhouse.linhasaereas.repository.ConfirmacaoRepository;
import com.devinhouse.linhasaereas.repository.PassageiroRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PassageiroServiceTest {

    @Mock
    private PassageiroRepository repository;

    @Mock
    private ConfirmacaoRepository confirmacaoRepository;


    @InjectMocks
    private PassageiroService service;

    List<Passageiro> listaPassageirosTeste = List.of(
            new Passageiro(11111111111L, "Silvana", LocalDate.now().minusYears(30), Classificacao.ASSOCIADO,100),
            new Passageiro(22222222222L, "Tiago The best of da categoria", LocalDate.now().minusYears(29), Classificacao.VIP,5000000),
            new Passageiro(12345678910L, "Leandro",  LocalDate.now().minusYears(10), Classificacao.PRATA,100),
            new Passageiro(12345678911L, "Ivan", LocalDate.now().minusYears(50), Classificacao.BRONZE,100),
            new Passageiro(12345678911L, "Cristiane", LocalDate.now().minusYears(20), Classificacao.OURO,100)
            );

    List<Confirmacao> listaConfirmacoes = List.of(
            new Confirmacao("djoiasjdoia", "1A", LocalDateTime.now(), true, listaPassageirosTeste.get(0))
    );

    @Test
    @DisplayName("Deve retornar lista de passageiros que não tem confirmação")
    void buscarPassageirosPendentes() {
        Mockito.when(repository.findAll()).thenReturn(listaPassageirosTeste);
        Mockito.when(confirmacaoRepository.findAll()).thenReturn(listaConfirmacoes);
        List<Passageiro> pendentes = service.buscarPassageirosPendentes();
        assertTrue(pendentes.stream().anyMatch(passageiro -> passageiro.getCpf().equals(listaPassageirosTeste.get(1).getCpf())));
        assertFalse(pendentes.stream().anyMatch(passageiro -> passageiro.getCpf().equals(listaPassageirosTeste.get(0).getCpf())));
    }

    @Test
    @DisplayName("Deve retornar true quando passageiro existe")
    void existPassageiroByCpf() {
        Mockito.when(repository.existsById(Mockito.anyLong())).thenReturn(true);
        Boolean existe = service.existPassageiroByCpf(12345678910L);
        assertEquals(true, existe);
    }

    @Test
    @DisplayName("Deve retornar false quando passageiro não existe")
    void inexistPassageiroByCpf() {
        Mockito.when(repository.existsById(Mockito.anyLong())).thenReturn(false);
        Boolean existe = service.existPassageiroByCpf(12345678910L);
        assertEquals(false, existe);
    }

    @Test
    @DisplayName("Deve retornar a lista de passageiros")
    void consultar() {
        Mockito.when(repository.findAll()).thenReturn(listaPassageirosTeste);
        List<Passageiro> resultado = service.consultar();
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(5, resultado.size());
        assertEquals(11111111111L,resultado.get(0).getCpf());
        assertEquals("Tiago The best of da categoria",resultado.get(1).getNome());
    }


    @Test
    @DisplayName("Quando há um passageiro cadastrado com o cpf, retorna um passageiro")
    void consultarPorCpf() {
        Passageiro passageiro = listaPassageirosTeste.get(0);
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(passageiro));
        Passageiro resultado = service.consultarPorCpf(passageiro.getCpf());
        assertNotNull(resultado);
        assertEquals(11111111111L,resultado.getCpf());
        assertEquals("Silvana",resultado.getNome());
    }

    @Test
    @DisplayName("Quando não há um passageiro cadastrado com o cpf, retorna um erro")
    void consultarPorCpfInexistente() {
        assertThrows(RegistroNaoEncontradoException.class, () -> service.consultarPorCpf(12345678910L));
    }

    @Test
    @DisplayName("Deve acrescentar 100 milhas ao passageiro se sua classificação for VIP")
    void acrescentarPontosVip() {
        Passageiro passageiroVip = listaPassageirosTeste.get(1);
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(passageiroVip));
        service.acrescentarPontos(passageiroVip.getCpf());
        assertEquals(5000100,passageiroVip.getMilhas());
    }

    @Test
    @DisplayName("Deve acrescentar 80 milhas ao passageiro se sua classificação for OURO")
    void acrescentarPontosOuro() {
        Passageiro passageiroOuro = listaPassageirosTeste.get(4);
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(passageiroOuro));
        service.acrescentarPontos(passageiroOuro.getCpf());
        assertEquals(180,passageiroOuro.getMilhas());
    }

    @Test
    @DisplayName("Deve acrescentar 50 milhas ao passageiro se sua classificação for PRATA")
    void acrescentarPontosPrata() {
        Passageiro passageiroPrata = listaPassageirosTeste.get(2);
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(passageiroPrata));
        service.acrescentarPontos(passageiroPrata.getCpf());
        assertEquals(150,passageiroPrata.getMilhas());
    }

    @Test
    @DisplayName("Deve acrescentar 30 milhas ao passageiro se sua classificação for BRONZE")
    void acrescentarPontosBronze() {
        Passageiro passageiroBronze = listaPassageirosTeste.get(3);
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(passageiroBronze));
        service.acrescentarPontos(passageiroBronze.getCpf());
        assertEquals(130,passageiroBronze.getMilhas());
    }

    @Test
    @DisplayName("Deve acrescentar 10 milhas ao passageiro se sua classificação for ASSOCIADO")
    void acrescentarPontosAssociado() {
        Passageiro passageiroAssociado = listaPassageirosTeste.get(0);
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(passageiroAssociado));
        service.acrescentarPontos(passageiroAssociado.getCpf());
        assertEquals(110,passageiroAssociado.getMilhas());
    }
    @Test
    @DisplayName("Retorna true quando passageiro é maior de idade")
    void verificarMaioridade() {
        Passageiro passageiro = listaPassageirosTeste.get(0);
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(passageiro));
        Boolean resultado = service.verificarMaioridade(passageiro.getCpf());
        assertEquals(true,resultado);
    }

    @Test
    @DisplayName("Retorna false quando passageiro é menor de idade")
    void verificarMenoridade() {
        Passageiro passageiro = listaPassageirosTeste.get(2);
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(passageiro));
        Boolean resultado = service.verificarMaioridade(passageiro.getCpf());
        assertEquals(false,resultado);
    }
}

