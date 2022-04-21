package com.writecode.rxjava.startup.repository;

import com.writecode.rxjava.startup.model.Usuario;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

import java.time.LocalDate;

public interface UsuarioRepository extends RxJava3CrudRepository<Usuario, String> {

    @Query("{ '_id' : ?0}")
    Flowable<Usuario> obtenerUsuarioPorId(String idUsuario);

    @Query("{ 'fechaNac' : { $gte: ?0, $lt: ?1}}")
    Flowable<Usuario> obtenerUsuariosPorFecha(LocalDate fechaInicio, LocalDate fechaFin);

}
