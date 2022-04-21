package com.writecode.rxjava.startup.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioResponse {

    private String id;
    private String nombres;
    private LocalDate fechaNac;
}
