package com.devinhouse.linhasaereas.controller;

import com.devinhouse.linhasaereas.dto.ConfirmacaoRequest;
import com.devinhouse.linhasaereas.model.Confirmacao;
import com.devinhouse.linhasaereas.repository.ConfirmacaoRepository;
import com.devinhouse.linhasaereas.repository.PassageiroRepository;
import com.devinhouse.linhasaereas.service.ConfirmacaoService;
import com.devinhouse.linhasaereas.service.PassageiroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class ConfirmacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // classe que serializa objetos para JSON

    @Autowired
    private ModelMapper modelMapper;

    @MockBean  // mock para dependencias da classe de controller
    private ConfirmacaoService confirmacaoService;

    @MockBean  // mock para dependencias da classe de controller
    private PassageiroService passageiroService;

    @MockBean
    private PassageiroRepository passageiroRepository;

    @MockBean
    private ConfirmacaoRepository confirmacaoRepository;

    @Test
    @DisplayName("Retorna sucesso quando inclusão de novo checkin")
    void inserir() throws Exception {
        ConfirmacaoRequest confirmacaoRequest = new ConfirmacaoRequest(22222222222L,"2A",true);
        Confirmacao confirmacao = modelMapper.map(confirmacaoRequest, Confirmacao.class);
        String requestJson = objectMapper.writeValueAsString(confirmacaoRequest);
        String eticket = "gatoGarfieldDeSapato";
        LocalDateTime agora = LocalDateTime.now();
        confirmacao.setEticket(eticket);
        confirmacao.setDataHoraConfirmacao(agora);
        Mockito.when(confirmacaoService.criar(Mockito.any(Confirmacao.class))).thenReturn(confirmacao);
        mockMvc.perform(post("/passageiros/confirmacao")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eticket",is(eticket)))
                .andExpect(jsonPath("$.dataHoraConfirmacao",is(not(empty())))); // 200;
    }

}
