package com.writecode.rxjava.startup.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FiltroDTO {

    private String idUsuario;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
