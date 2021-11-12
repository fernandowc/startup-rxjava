package com.writecode.rxjava.startup.repository;

import com.writecode.rxjava.startup.model.Usuario;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;;

public interface UsuarioRepository extends RxJava3CrudRepository<Usuario, String> {

}
