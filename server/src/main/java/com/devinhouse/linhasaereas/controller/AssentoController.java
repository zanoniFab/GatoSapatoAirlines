package com.devinhouse.linhasaereas.controller;

import com.devinhouse.linhasaereas.dto.AssentoResponse;
import com.devinhouse.linhasaereas.service.ConfirmacaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/assentos")
@AllArgsConstructor
public class AssentoController {

    private ConfirmacaoService checkInService;

    @GetMapping
    public ResponseEntity<List<AssentoResponse>> ListarOcupacaoAssentos (){
        List<String> assentos =  checkInService.consultarAssentos();
        List<AssentoResponse> resp = new ArrayList<>();
        for (String assento: assentos){
            Boolean isOcupado = checkInService.verificarDisponibilidade(assento);
            AssentoResponse assentoResponse = new AssentoResponse(assento, isOcupado);
            resp.add(assentoResponse);
        }
        return ResponseEntity.ok(resp);
    }

}