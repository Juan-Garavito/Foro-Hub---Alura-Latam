package com.juangaravito.forohub.dominio.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoDatosPeticion(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        Integer autor,
        @NotNull
        Integer curso
) {
}
