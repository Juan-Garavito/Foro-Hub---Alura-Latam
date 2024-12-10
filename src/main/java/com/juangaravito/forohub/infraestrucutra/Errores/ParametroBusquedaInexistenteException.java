package com.juangaravito.forohub.infraestrucutra.Errores;

public class ParametroBusquedaInexistenteException extends RuntimeException {

    private String parametro;

    public ParametroBusquedaInexistenteException(String parametro, String message) {
        super(message);
        this.parametro = parametro;
    }

    public String getParametro() {
        return parametro;
    }
}
