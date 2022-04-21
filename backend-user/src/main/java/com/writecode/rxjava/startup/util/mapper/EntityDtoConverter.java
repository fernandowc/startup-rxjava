package com.writecode.rxjava.startup.util.mapper;

import com.writecode.rxjava.startup.dto.UsuarioResponse;
import com.writecode.rxjava.startup.model.Usuario;
import io.reactivex.rxjava3.core.Flowable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityDtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioResponse convertEntityToDTO(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioResponse.class);
    }

    public Flowable<UsuarioResponse> convert(Flowable<Usuario> usuarios) {
        return usuarios.map(this::convertEntityToDTO);
    }



}
