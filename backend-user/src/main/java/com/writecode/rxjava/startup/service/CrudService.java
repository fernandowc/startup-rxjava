package com.writecode.rxjava.startup.service;

import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface CrudService<T, ID> {

    Mono<T> registrar(T t);
    Mono<T> modificar(T t);
    Flux<T> listar();
    Mono<T> listarById(ID id);
    Mono<Void> eliminar(ID id);
    //Mono<PageSupport<T>> listarPage(Pageable page);
}
