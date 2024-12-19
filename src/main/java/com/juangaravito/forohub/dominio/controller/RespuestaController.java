package com.juangaravito.forohub.dominio.controller;

import com.juangaravito.forohub.dominio.DTO.RespuestaDTO;
import com.juangaravito.forohub.dominio.DTO.TopicoConRespuestas;
import com.juangaravito.forohub.dominio.DTO.TopicoDTO;
import com.juangaravito.forohub.dominio.DTO.UsuarioRespuesta;
import com.juangaravito.forohub.dominio.model.Respuesta;
import com.juangaravito.forohub.dominio.model.Topico;
import com.juangaravito.forohub.dominio.model.Usuario;
import com.juangaravito.forohub.dominio.service.RespuestaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/respuesta")
public class RespuestaController {

    private RespuestaService respuestaService;

    public RespuestaController(RespuestaService respuestaService) {
        this.respuestaService = respuestaService;
    }

    @GetMapping("/topico/{id}")
    public ResponseEntity<TopicoConRespuestas> obtenerRespuestasPorIdDeTopico(@PathVariable int id){
        Topico topico = respuestaService.obtenerRespuestasPorIdDeTopico(id);
        List<Respuesta> respuestas = topico.getRespuestas();
        TopicoDTO topicoDTO = new TopicoDTO(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getCurso().getNombre(),
                topico.getCurso().getCategoria());
        List<RespuestaDTO> respuestaDTOS = respuestas.stream().map(respuesta -> {
            return new RespuestaDTO(respuesta.getId(),
                    respuesta.getMensaje(),
                    respuesta.getFechaCreacion(),
                    respuesta.getSolucion(),
                    new UsuarioRespuesta(respuesta.getAutor().getNombre(), respuesta.getAutor().getCorreo()));
        }).collect(Collectors.toList());

        TopicoConRespuestas topicoConRespuestas = new TopicoConRespuestas(topicoDTO, respuestaDTOS);
        return ResponseEntity.ok(topicoConRespuestas);
    }
}
