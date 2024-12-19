package com.juangaravito.forohub.dominio.controller;

import com.juangaravito.forohub.dominio.DTO.TopicoDTO;
import com.juangaravito.forohub.dominio.DTO.UsuarioDTO;
import com.juangaravito.forohub.dominio.model.Topico;
import com.juangaravito.forohub.dominio.model.Usuario;
import com.juangaravito.forohub.dominio.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable int id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        List<Topico> topicosUsuario =  usuario.getTopicos();
        List<TopicoDTO> topicoDTOS = topicosUsuario.stream().map(topico -> {
          return new TopicoDTO(topico.getId(),
                  topico.getTitulo(),
                  topico.getMensaje(),
                  topico.getFechaCreacion(),
                  topico.getCurso().getNombre(),
                  topico.getCurso().getCategoria());
        }).collect(Collectors.toList());
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getNombre(), usuario.getCorreo(), topicoDTOS);
        return ResponseEntity.ok(usuarioDTO);
    }
}
