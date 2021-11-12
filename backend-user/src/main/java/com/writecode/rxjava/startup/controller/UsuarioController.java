package com.writecode.rxjava.startup.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.writecode.rxjava.startup.model.Usuario;
import com.writecode.rxjava.startup.service.usuario.UsuarioService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping(produces = "application/stream+json")
    public Single<ResponseEntity<Flowable<Usuario>>> listar()
    {
        Flowable<Usuario> fxUsuarios = usuarioService.listar();
        return Single.just(ResponseEntity
                .ok()
                .body(fxUsuarios));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ResponseEntity<Usuario>> nuevo(@RequestBody Usuario usuario)
    {
        return usuarioService.registrar(usuario)
                .subscribeOn(Schedulers.io())
                .map(p -> ResponseEntity.created(URI.create("/"+ p.getId()))
                .body(usuario));

    }

    @GetMapping("/{id}")
    public Maybe<ResponseEntity<Usuario>> listarPorId(@PathVariable("id") String id)
    {
        return usuarioService.listarPorId(id)
                .subscribeOn(Schedulers.io())
                .map(p -> ResponseEntity.ok()
                .body(p));
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
