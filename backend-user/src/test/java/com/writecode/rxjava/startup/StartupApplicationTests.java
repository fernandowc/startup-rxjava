package com.writecode.rxjava.startup;

import com.writecode.rxjava.startup.repository.UsuarioRepository;
import com.writecode.rxjava.startup.service.usuario.UsuarioService;
import com.writecode.rxjava.startup.service.usuario.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StartupApplicationTests {

    UsuarioRepository usuarioRepository;

    UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioRepository = mock(UsuarioRepository.class);
        usuarioService = new UsuarioServiceImpl();
    }

    @Test
    void contextLoads() {
    }

}
