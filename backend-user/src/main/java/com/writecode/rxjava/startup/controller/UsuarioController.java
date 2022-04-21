package com.writecode.rxjava.startup.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.writecode.rxjava.startup.dto.FiltroDTO;
import com.writecode.rxjava.startup.dto.UsuarioResponse;
import com.writecode.rxjava.startup.model.Usuario;
import com.writecode.rxjava.startup.service.usuario.UsuarioService;
import com.writecode.rxjava.startup.service.usuario.UsuarioServiceImpl;
import com.writecode.rxjava.startup.util.Exception.ApiExceptionEnum;
import com.writecode.rxjava.startup.util.Exception.exception.ApiRequestException;
import com.writecode.rxjava.startup.util.mapper.EntityDtoConverter;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.schedulers.Schedulers;
import net.minidev.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static reactor.function.TupleUtils.function;

@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioController {

    final static Logger log = Logger.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EntityDtoConverter converter;


    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Single<ResponseEntity<Flowable<UsuarioResponse>>> listar()
    {
        Flowable<Usuario> fxUsuarios = usuarioService.listar();
        return Single.just(ResponseEntity
                .ok()
                .body(converter.convert(fxUsuarios)));
    }

    @PostMapping
    public Single<ResponseEntity<Usuario>> nuevo(@Valid @RequestBody Usuario usuario)
    {
//        return usuarioService.registrar(usuario)
//                .subscribeOn(Schedulers.io())
//                .map(p -> ResponseEntity.created(URI.create("/"+ p.getId()))
//                .body(usuario));

        return usuarioService.registrar(usuario)
                .map(p -> ResponseEntity.created(URI.create("/"+ p.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(usuario));


    }

    @GetMapping("/{id}")
    public Single<ResponseEntity<Usuario>> listarPorId(@PathVariable("id") String id)
    {

        return usuarioService.listarPorId(id)
                .map(p -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public Single<ResponseEntity<Usuario>> modificar(@PathVariable("id") String id, @RequestBody Usuario usuario)
    {
        Single<Usuario> singleBody = Single.just(usuario);
        Single<Usuario> singleDB = usuarioService.listarPorId(id).toSingle();
        return singleDB.zipWith(singleBody,(bd,us) -> {
            bd.setId(id);
            bd.setNombres(us.getNombres());
            bd.setApellidos(us.getApellidos());
            bd.setFechaNac(us.getFechaNac());
            bd.setUrlFoto(us.getUrlFoto());
            return bd;
        }).flatMap(usuarioService::modificar)
                .map(p -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(p));
    }

    @DeleteMapping("{id}")
    public Single<ResponseEntity<Void>> eliminarUsuario(@PathVariable("id") String id) {

        return usuarioService.eliminarById(id).subscribeOn(Schedulers.io())
                .toSingle(() -> new ResponseEntity<>(HttpStatus.OK));
    }

    @PostMapping("/buscar")
    public Single<ResponseEntity<Flowable<Usuario>>> buscar(@RequestBody FiltroDTO filtro) {

        Flowable<Usuario> flwUsuarios = usuarioService.obtenerUsuarioPorFiltro(filtro);

        return Single.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flwUsuarios));

    }

    @GetMapping("/generarReporte/{id}")
    public Single<ResponseEntity<byte[]>> generarReporte(@PathVariable("id") String id) {

        Maybe<byte[]> reporte = usuarioService.generarReporte(id);

        return reporte.map(bytes -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(bytes))
                .defaultIfEmpty(new ResponseEntity<byte[]>(HttpStatus.NO_CONTENT));
    }

    @GetMapping("/hateoas/{id}")
    public Single<EntityModel<Usuario>> listarHateoasPorId(@PathVariable("id") String id) {

        Single<Link> link1 = Single.just(linkTo(methodOn(UsuarioController.class).listarPorId(id)).withSelfRel());

        //practica IDEAL
        return usuarioService.listarPorId(id).toSingle()
                .zipWith(link1, EntityModel::of);
    }



//    @PostMapping("/subir/{id}")
//    public Single<ResponseEntity<Usuario>> subirFoto(@PathVariable String id, @RequestPart FilePart file) throws IOException
//    {
//        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
//                "cloud_name", "dqccbt9ko",
//                "api_key", "738857697228694",
//                "api_secret", "QS64lW23ZvbLlVcJvVFxPz78nmU"
//        ));
//        File f = Files.createTempFile("temp", file.filename()).toFile();
//        return file.transferTo(f)
//                .then(usuarioService.listarPorId(id)
//                .flatMap(c -> {
//                    Map response;
//                    try {
//                        response = cloudinary.uploader().upload(f, ObjectUtils.asMap("resource_type", "auto"));
//                        JSONObject json = new JSONObject(response);
//                        String url = json.getAsString("url");
//
//                        c.setUrlFoto(url);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    return usuarioService.modificar(c);
//                })
//                        .defaultIfEmpty(ResponseEntity.notFound().build())
//                );
//    }



}
