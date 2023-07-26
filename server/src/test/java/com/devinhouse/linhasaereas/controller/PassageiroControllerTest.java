package com.devinhouse.linhasaereas.controller;

import com.devinhouse.linhasaereas.model.Classificacao;
import com.devinhouse.linhasaereas.model.Passageiro;
import com.devinhouse.linhasaereas.repository.ConfirmacaoRepository;
import com.devinhouse.linhasaereas.repository.PassageiroRepository;
import com.devinhouse.linhasaereas.service.ConfirmacaoService;
import com.devinhouse.linhasaereas.service.PassageiroService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class PassageiroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PassageiroService passageiroService;

    @InjectMocks
    @Autowired
    private PassageiroController passageiroController;

    @MockBean
    private ConfirmacaoRepository confirmacaoRepository;

    @MockBean
    private PassageiroRepository passageiroRepository;

    @MockBean
    private AssentoController assentoController;

    @MockBean
    private ConfirmacaoController confirmacaoController;

    @MockBean
    private ConfirmacaoService confirmacaoService;

    @Test
    @DisplayName("Quando há passageiros registrados, deve retornar lista com passageiros")
    void consultar() throws Exception{
        var passageiros = List.of(
                new Passageiro(12345678910L, "Silvana",  LocalDate.of(1977, Month.JANUARY, 14), Classificacao.VIP,100),
                new Passageiro(12345678911L, "Ivan", LocalDate.of(1977, Month.SEPTEMBER, 27), Classificacao.VIP,100)
        );
        Mockito.when(passageiroService.consultar()).thenReturn(passageiros);
        mockMvc.perform(get("/passageiros")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].cpf",is(passageiros.get(0).getCpf())))
        ;
    }

}
