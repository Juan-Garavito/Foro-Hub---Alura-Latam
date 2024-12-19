package com.juangaravito.forohub.dominio.DTO;

import com.juangaravito.forohub.dominio.model.Topico;
import com.juangaravito.forohub.dominio.model.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public record RespuestaDTO(
        Integer id,
        String mensaje,

        LocalDateTime fechaCreacion,

        String solucion,

        UsuarioRespuesta autor

) {
}
