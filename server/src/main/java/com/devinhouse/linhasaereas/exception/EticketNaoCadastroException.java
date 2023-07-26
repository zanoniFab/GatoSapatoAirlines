package com.devinhouse.linhasaereas.exception;

public class EticketNaoCadastroException extends RuntimeException {
    public EticketNaoCadastroException() {
        super("E-ticket não encontrado.");
    }

}
