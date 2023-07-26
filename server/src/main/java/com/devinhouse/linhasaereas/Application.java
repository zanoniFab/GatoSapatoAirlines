package com.devinhouse.linhasaereas;

import com.devinhouse.linhasaereas.dto.ConfirmacaoRequest;
import com.devinhouse.linhasaereas.model.Confirmacao;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(ConfirmacaoRequest.class, Confirmacao.class).addMapping(src -> src.getCpf(), (dest, v) -> dest.getPassageiro().setCpf((Long) v));
        return modelMapper;
    }

}
