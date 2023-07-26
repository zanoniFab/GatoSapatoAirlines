package com.devinhouse.linhasaereas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssentoResponse {

    private String assento;

    private Boolean ocupado;


}