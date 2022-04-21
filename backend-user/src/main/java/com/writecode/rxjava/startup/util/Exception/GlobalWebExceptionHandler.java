package com.writecode.rxjava.startup.util.Exception;
//
//import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
//import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
//import org.springframework.boot.web.error.ErrorAttributeOptions;
//import org.springframework.boot.web.reactive.error.ErrorAttributes;
//import org.springframework.context.ApplicationContext;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.codec.ServerCodecConfigurer;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.BodyInserters;
//import org.springframework.web.reactive.function.server.*;
//import reactor.core.publisher.Mono;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//@Order(-1)
//public class GlobalWebExceptionHandler extends AbstractErrorWebExceptionHandler{
//
//    public GlobalWebExceptionHandler(ErrorAttributes errorAttributes, Resources resourceProperties,
//                                     ApplicationContext applicationContext, ServerCodecConfigurer configurer) {
//        super(errorAttributes, resourceProperties, applicationContext);
//        this.setMessageWriters(configurer.getWriters());
//    }
//
//    @Override
//    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
//        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse); // req ->
//        // this.renderErrorResponse(req)
//    }
//
//    private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
//        Map<String, Object> errorGeneral = getErrorAttributes(request, ErrorAttributeOptions.defaults());
//        Map<String, Object> mapException = new HashMap<>();
//
//        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//        String statusCode = String.valueOf(errorGeneral.get("status"));
//
//        switch (statusCode) {
//            case "500":
//                mapException.put("code", "500");
//                mapException.put("excepcion", "Error general del backend");
//                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//                break;
//
//            case "400":
//                mapException.put("code", "400");
//                mapException.put("excepcion", "Peticion incorrecta");
//                httpStatus = HttpStatus.BAD_REQUEST;
//                break;
//            case "406":
//                mapException.put("code", "406");
//                mapException.put("excepcion", "Archivo no subido correctamente");
//                httpStatus = HttpStatus.NOT_ACCEPTABLE;
//                break;
//            default:
//                mapException.put("code", "900");
//                mapException.put("excepcion", errorGeneral.get("error"));
//                httpStatus = HttpStatus.CONFLICT;
//                break;
//        }
//
//        return ServerResponse.status(httpStatus).contentType(MediaType.APPLICATION_JSON)
//                .body(BodyInserters.fromValue(mapException));
//    }
//
//}
