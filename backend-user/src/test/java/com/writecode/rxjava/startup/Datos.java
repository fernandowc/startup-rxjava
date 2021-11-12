package com.writecode.rxjava.startup;

import com.writecode.rxjava.startup.model.Usuario;
import io.reactivex.rxjava3.core.Flowable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Datos {
    public final static Flowable<Usuario> USUARIOS = Flowable.just(new Usuario("1","luis","vidal",LocalDate.now(),"asd"));
}
