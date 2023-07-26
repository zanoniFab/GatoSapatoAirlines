package com.devinhouse.linhasaereas.controller;

import com.devinhouse.linhasaereas.dto.PassageiroPendenteResponse;
import com.devinhouse.linhasaereas.dto.PassageiroResponse;
import com.devinhouse.linhasaereas.model.Confirmacao;
import com.devinhouse.linhasaereas.model.Passageiro;
import com.devinhouse.linhasaereas.service.ConfirmacaoService;
import com.devinhouse.linhasaereas.service.PassageiroService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/passageiros")
@AllArgsConstructor
public class PassageiroController {

    private PassageiroService passageiroService;
    private ConfirmacaoService confirmacaoService;
    private ModelMapper mapper;
    @GetMapping
    public ResponseEntity<List<PassageiroResponse>> consultar(){
        List<Passageiro> listaPassageiros = passageiroService.consultar();
        List<PassageiroResponse> resp = listaPassageiros.stream().map(s -> mapper.map(s, PassageiroResponse.class)).collect(Collectors.toList());
        for (PassageiroResponse passageiro: resp) {
            Confirmacao confirmacao = confirmacaoService.consultarPorCpf(passageiro.getCpf());
            if (confirmacao != null){
                passageiro.setEticket(confirmacao.getEticket());
                passageiro.setAssento(confirmacao.getAssento());
            }
        }
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<PassageiroPendenteResponse>> buscarPassageirosPendentes(){
        List<Passageiro> listaPassageiros = passageiroService.buscarPassageirosPendentes();
        List<PassageiroPendenteResponse> resp = listaPassageiros.stream().map(passageiro -> mapper.map(passageiro, PassageiroPendenteResponse.class)).collect(Collectors.toList());
        return ResponseEntity.ok(resp);
    }
}
