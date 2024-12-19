package com.juangaravito.forohub.dominio.service;

import com.juangaravito.forohub.dominio.model.Respuesta;
import com.juangaravito.forohub.dominio.model.Topico;
import com.juangaravito.forohub.dominio.repository.RespuestaRepository;
import com.juangaravito.forohub.dominio.repository.TopicoRepository;
import com.juangaravito.forohub.infraestrucutra.Errores.ParametroBusquedaInexistenteException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RespuestaService {

    private RespuestaRepository respuestaRepository;
    private TopicoRepository topicoRepository;

    public RespuestaService(RespuestaRepository respuestaRepository, TopicoRepository topicoRepository) {
        this.respuestaRepository = respuestaRepository;
        this.topicoRepository = topicoRepository;
    }

    public Topico obtenerRespuestasPorIdDeTopico(int id) {
        Topico topico = topicoRepository.findById(id).orElse(null);
        System.out.println(id);
        System.out.println(topico);
        if (topico == null){
            throw new ParametroBusquedaInexistenteException("id", "No existe");
        }

        return topico;
    }
}
