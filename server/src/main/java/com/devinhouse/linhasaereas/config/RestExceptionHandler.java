package com.devinhouse.linhasaereas.config;

import com.devinhouse.linhasaereas.dto.ErroResponse;
import com.devinhouse.linhasaereas.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity<Object> handleRegistroNaoEncontradoException(RegistroNaoEncontradoException e) {
        ErroResponse erro = new ErroResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(EticketNaoCadastroException.class)
    public ResponseEntity<Object> handleEticketNaoCadastroException(EticketNaoCadastroException e) {
        ErroResponse erro = new ErroResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(RegistroEncontradoException.class)
    public ResponseEntity<Object> handleRegistroEncontradoException(RegistroEncontradoException e) {
        ErroResponse erro = new ErroResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(MalaNaoDespachadaException.class)
    public ResponseEntity<Object> handleMalaNaoDespachadaException(MalaNaoDespachadaException e) {
        ErroResponse erro = new ErroResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(PassageiroMenorDeIdadeException.class)
    public ResponseEntity<Object> handlePassageiroMenorDeIdadeException(PassageiroMenorDeIdadeException e) {
        ErroResponse erro = new ErroResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

}
