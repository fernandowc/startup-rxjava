package com.writecode.rxjava.startup.service.usuario;

import com.writecode.rxjava.startup.dto.FiltroDTO;
import com.writecode.rxjava.startup.model.Usuario;
import com.writecode.rxjava.startup.pagination.PageSupport;
import com.writecode.rxjava.startup.repository.UsuarioRepository;
import com.writecode.rxjava.startup.util.Exception.ApiExceptionEnum;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class UsuarioServiceImpl implements UsuarioService{

    final static Logger log = Logger.getLogger(UsuarioServiceImpl.class);

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
        return usuarioRepository.save(s)
                .doOnError(error -> log.error(ApiExceptionEnum.ER0001));
    }

    @Override
    public Single<Usuario> modificar(Usuario id) {
        return usuarioRepository.save(id);
    }

    @Override
    public Maybe<Usuario> listarPorId(String id) {
        return usuarioRepository.findById(id)
                .doOnError(error -> log.error(ApiExceptionEnum.ER0001));
    }

    @Override
    public Completable eliminarById(String id) {
        return usuarioRepository.deleteById(id);
    }

    @Override
    public Flowable<Usuario> obtenerUsuarioPorFiltro(FiltroDTO filtro) {
        String criterio = filtro.getIdUsuario() != null ? "C" : "O";

        if(criterio.equalsIgnoreCase("C"))
        {
            return usuarioRepository.obtenerUsuarioPorId(filtro.getIdUsuario());
        }else {

            return usuarioRepository.obtenerUsuariosPorFecha(filtro.getFechaInicio(), filtro.getFechaFin());
        }
    }

    @Override
    public Maybe<byte[]> generarReporte(String id) {
        return usuarioRepository.findById(id)
                .map(f -> {
                    InputStream stream;
                    try {
                        Map<String, Object> parametros = new HashMap<String, Object>();
                        parametros.put("txt_cliente", f.getNombres());
                        parametros.put("txt_apellido", f.getApellidos());

                        stream = getClass().getResourceAsStream("/facturaSpringReactor.jrxml");
                        JasperReport report = JasperCompileManager.compileReport(stream);
                        JasperPrint print = JasperFillManager.fillReport(report, parametros);
                        return JasperExportManager.exportReportToPdf(print);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return new byte[0];
                });
    }
//
//    private Completable eliminarUsuario(String id) {
//
//        return Completable.create(compSub -> {
//            Maybe<Usuario> user = usuarioRepository.findById(id);
//            user.defaultIfEmpty()
//            usuarioRepository.deleteById(id);
//            compSub.onComplete();
//        });
//    }

}
