package com.writecode.rxjava.startup.service.usuario;

import com.writecode.rxjava.startup.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioServiceImplTest {

    @Mock private UsuarioService usuarioService;

    @Test
    void listar() {
    }

    @Test
    void registrar() {
    }

    @Test
    void modificar() {
    }

    @Test
    void listarPorId() {

        usuarioService.listarPorId("61273025a2056141c33317cb");
    }
}