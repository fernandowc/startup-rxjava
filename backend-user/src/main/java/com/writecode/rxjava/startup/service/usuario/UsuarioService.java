package com.writecode.rxjava.startup.service.usuario;

import com.writecode.rxjava.startup.dto.FiltroDTO;
import com.writecode.rxjava.startup.model.Usuario;
import com.writecode.rxjava.startup.pagination.PageSupport;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {

    Flowable<Usuario> listar();
    Single<Usuario> registrar(Usuario s);
    Single<Usuario> modificar(Usuario id);
    Maybe<Usuario> listarPorId(String id);
    Completable eliminarById(String id);

    Flowable<Usuario> obtenerUsuarioPorFiltro(FiltroDTO filtro);
    Maybe<byte[]> generarReporte(String id);

}
