package com.juangaravito.forohub.infraestrucutra.Errores;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ControllerErrores {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity gestionaErroresDeCamposEntidades(MethodArgumentNotValidException exeception){
        List<MensajeErrorCampoValidacion> mensajes = exeception.getFieldErrors().stream()
                .map(error -> new MensajeErrorCampoValidacion(error.getField(), error.getDefaultMessage())).toList();
        return ResponseEntity.badRequest().body(mensajes);
    }

    @ExceptionHandler(TopicoDuplicadoExecption.class)
    public ResponseEntity gestionarErroresDeTopicoDuplicado(TopicoDuplicadoExecption execption) {
        return ResponseEntity.badRequest().body(execption.getMessage());
    }

    @ExceptionHandler(RegistroInexistenteException.class)
    public ResponseEntity gestionarErroresRegistroInexistente(RegistroInexistenteException execption) {
        return ResponseEntity.badRequest().body(execption.getMessage());
    }

    @ExceptionHandler(ParametroBusquedaInexistenteException.class)
    public ResponseEntity gestionarErroresParametros(ParametroBusquedaInexistenteException exception){
        MensajeErrorCampoValidacion mensaje = new MensajeErrorCampoValidacion(exception.getParametro(), exception.getMessage());
        return ResponseEntity.badRequest().body(mensaje);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity gestionarErroresFaltaBodyEnPeticion(){
        MensajeErrorCampoValidacion mensaje = new MensajeErrorCampoValidacion("Body","Debes enviar un payload en JSON");
        return ResponseEntity.badRequest().body(mensaje);
    }
}
