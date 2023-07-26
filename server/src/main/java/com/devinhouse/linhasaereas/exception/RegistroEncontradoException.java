package com.devinhouse.linhasaereas.exception;

public class RegistroEncontradoException extends RuntimeException {

    public RegistroEncontradoException(Long cpf) {
        super("Checkin para o cpf: " + cpf + " foi realizado anteriormente.");
    }

    public RegistroEncontradoException(String assento) {
        super("O assento " + assento + " já está ocupado");
    }
}
