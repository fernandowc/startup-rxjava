package com.writecode.rxjava.startup.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioServiceConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
