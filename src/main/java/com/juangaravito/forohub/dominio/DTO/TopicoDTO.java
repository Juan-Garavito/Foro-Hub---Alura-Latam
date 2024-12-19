package com.juangaravito.forohub.dominio.DTO;

import com.juangaravito.forohub.dominio.model.CursoCategoria;


import java.time.LocalDateTime;

public record TopicoDTO (
        Integer id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String nombreCurso,
        CursoCategoria categoriaCurso
){

}
