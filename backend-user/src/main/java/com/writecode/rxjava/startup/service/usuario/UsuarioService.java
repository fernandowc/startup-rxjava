package com.writecode.rxjava.startup.service.usuario;

import com.writecode.rxjava.startup.model.Usuario;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface UsuarioService {

    Flowable<Usuario> listar();
    Single<Usuario> registrar(Usuario s);
    Single<Usuario> modificar(Usuario id);
    Maybe<Usuario> listarPorId(String id);

}
