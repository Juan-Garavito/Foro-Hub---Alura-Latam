package com.juangaravito.forohub.dominio.DTO;

import java.time.LocalDateTime;

public record TopicoDatosRespuesta(

        Integer id,
        String titulo,
        String mensaje,
        Integer autor,
        LocalDateTime fechaCreacion,
        Integer curso
) {

}
