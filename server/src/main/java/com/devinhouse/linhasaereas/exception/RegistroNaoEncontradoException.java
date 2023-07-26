package com.devinhouse.linhasaereas.exception;

public class RegistroNaoEncontradoException extends RuntimeException {

    public RegistroNaoEncontradoException(Long cpf){
        super("O cpf " + cpf + " não foi encontrado.");
    }

    public RegistroNaoEncontradoException(String assento) {
        super("O assento " + assento + " não foi encontrado");
    }
}
