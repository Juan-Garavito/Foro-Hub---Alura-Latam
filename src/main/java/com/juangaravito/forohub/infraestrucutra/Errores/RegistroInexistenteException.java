package com.juangaravito.forohub.infraestrucutra.Errores;

public class RegistroInexistenteException extends RuntimeException {
    public RegistroInexistenteException(String mensaje) {
       super(mensaje);
    }
}
