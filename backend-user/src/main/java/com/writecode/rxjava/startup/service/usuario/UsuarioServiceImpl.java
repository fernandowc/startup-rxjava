package com.writecode.rxjava.startup.service.usuario;

import com.writecode.rxjava.startup.model.Usuario;
import com.writecode.rxjava.startup.repository.UsuarioRepository;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Flowable<Usuario> listar() {
        return usuarioRepository.findAll();/*
                .subscribeOn(Schedulers.io())
                .filter(usuario -> true)
                .map(usuario -> {
                    usuario.setNombres(usuario.getNombres().replaceFirst(,usuario.getNombres().toUpperCase()));
                    return usuario;
                });*/
    }

    @Override
    public Single<Usuario> registrar(Usuario s) {
        return usuarioRepository.save(s);
    }

    @Override
    public Single<Usuario> modificar(Usuario id) {
        return usuarioRepository.save(id);
    }

    @Override
    public Maybe<Usuario> listarPorId(String id) {
        return usuarioRepository.findById(id);
    }

}
