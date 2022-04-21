package com.writecode.rxjava.startup.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;

    @NotNull
    @Size(min = 3)
    @Field(name = "nombres")
    private String nombres;

    @NotNull
    @Size(min = 3)
    @Field(name = "apellidos")
    private String apellidos;

    @NotNull
    @Field(name = "fechaNac")
    private LocalDate fechaNac;

    @Field(name = "urlFoto")
    private String urlFoto;

}
