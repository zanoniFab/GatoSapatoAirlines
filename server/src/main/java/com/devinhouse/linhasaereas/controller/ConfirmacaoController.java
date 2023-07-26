package com.devinhouse.linhasaereas.controller;

import com.devinhouse.linhasaereas.dto.ConfirmacaoCompletoResponse;
import com.devinhouse.linhasaereas.dto.ConfirmacaoRequest;
import com.devinhouse.linhasaereas.dto.ConfirmacaoResponse;
import com.devinhouse.linhasaereas.model.Confirmacao;
import com.devinhouse.linhasaereas.model.Passageiro;
import com.devinhouse.linhasaereas.service.ConfirmacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/passageiros/confirmacao")
@AllArgsConstructor
public class ConfirmacaoController {

    private ConfirmacaoService confirmacaoService;

    private ModelMapper mapper;


    @GetMapping("{eticket}")
    public ResponseEntity<ConfirmacaoCompletoResponse> buscarPorEticket(@PathVariable("eticket") String eticketRequest) {
        Confirmacao confirmacao = confirmacaoService.buscarPorEticket(eticketRequest);
        ConfirmacaoCompletoResponse confirmacaoCompletoResponse = mapper.map(confirmacao, ConfirmacaoCompletoResponse.class);
        Passageiro passageiro = confirmacao.getPassageiro();
        confirmacaoCompletoResponse.setCpf(passageiro.getCpf());
        confirmacaoCompletoResponse.setNome(passageiro.getNome());
        confirmacaoCompletoResponse.setClassificacao(passageiro.getClassificacao());
        return ResponseEntity.ok(confirmacaoCompletoResponse);
    }


    @PostMapping
    public ResponseEntity<ConfirmacaoResponse> inserir(@RequestBody @Valid ConfirmacaoRequest confirmacaoRequest) {
        Confirmacao confirmacao = mapper.map(confirmacaoRequest, Confirmacao.class);
        confirmacao = confirmacaoService.criar(confirmacao);
        ConfirmacaoResponse confirmacaoResponse = mapper.map(confirmacao,ConfirmacaoResponse.class);
        return ResponseEntity.ok(confirmacaoResponse);
    }

}
