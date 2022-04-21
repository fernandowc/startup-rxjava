package com.writecode.rxjava.startup.util.Exception;


import com.cloudinary.api.exceptions.ApiException;
import com.writecode.rxjava.startup.util.Constants.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiExceptionEnum {

    ER0001(Constant.ER0001,"No se pudo encontrar registros para esta peticion","NOT FOUND", Constant.API),
    ER0002(Constant.ER0002,"No se pudo crear este registro","NOT CREATED", Constant.API);

    private final String code;
    private final String description;
    private final String errorCategory;
    private final String componentName;



}
