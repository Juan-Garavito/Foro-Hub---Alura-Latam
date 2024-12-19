package com.juangaravito.forohub.dominio.DTO;

import com.juangaravito.forohub.dominio.model.Topico;

import java.util.List;

public record TopicoConRespuestas(
        TopicoDTO topico,
        List<RespuestaDTO> respuestas
) {

}
