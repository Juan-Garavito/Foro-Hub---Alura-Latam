package com.juangaravito.forohub.dominio.DTO;

import java.util.List;

public record UsuarioDTO(
        String nombre,
        String correo,
        List<TopicoDTO> topicos
) {
}
