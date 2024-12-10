package com.juangaravito.forohub.dominio.service;

import com.juangaravito.forohub.dominio.DTO.TopicoDatosPeticion;
import com.juangaravito.forohub.dominio.Validaciones.ValidacionesTopico;
import com.juangaravito.forohub.dominio.model.Curso;
import com.juangaravito.forohub.dominio.model.Topico;
import com.juangaravito.forohub.dominio.model.Usuario;
import com.juangaravito.forohub.dominio.repository.CursoRepository;
import com.juangaravito.forohub.dominio.repository.TopicoRepository;
import com.juangaravito.forohub.dominio.repository.UsuarioRepository;
import com.juangaravito.forohub.infraestrucutra.Errores.ParametroBusquedaInexistenteException;
import com.juangaravito.forohub.infraestrucutra.Errores.RegistroInexistenteException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TopicoService {

    private UsuarioRepository usuarioRepository;
    private CursoRepository cursoRepository;
    private TopicoRepository topicoRepository;
    private List<ValidacionesTopico> validaciones;

    public TopicoService(UsuarioRepository usuarioRepository, CursoRepository cursoRepository, TopicoRepository topicoRepository, List<ValidacionesTopico> validaciones) {
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
        this.topicoRepository = topicoRepository;
        this.validaciones = validaciones;
    }

    public Topico guardarTopico(TopicoDatosPeticion topico){

        validaciones.forEach(validaciones -> validaciones.validar(topico));

        Usuario autor = usuarioRepository.findById(topico.autor()).orElse(null);
        Curso curso = cursoRepository.findById(topico.curso()).orElse(null);

        if(autor == null || curso == null){
            throw new RegistroInexistenteException("Usuario o curso no existen");
        }

        Topico topicoAGuardar = new Topico(topico, autor, curso);
        return topicoRepository.save(topicoAGuardar);
    }

    public Page<Topico> buscarTodosTopicos(Pageable pageable) {
        return topicoRepository.findAll(pageable);
    }

    public Page<Topico> buscarTodosTopicosPorNombreDeCurso(String curso, Pageable pageable) {
        return topicoRepository.findAllByNombreCurso(curso, pageable);
    }

    public Page<Topico> buscarTodosTopicosPorFecha(int año, Pageable pageable) {
        LocalDateTime fechaMinima = LocalDateTime.of(año, 1, 1, 0, 0);
        LocalDateTime fechaMaxima = LocalDateTime.of(año, 12, 31, 23, 59);
        return topicoRepository.findAllByFecha(fechaMinima, fechaMaxima, pageable);
    }

    public Topico buscarTopicoPorId(int idTopico) {
        Topico topico =  topicoRepository.findById(idTopico).orElse(null);
        if(topico == null){
            throw new ParametroBusquedaInexistenteException("id", "Este topico no existe");
        }

        return topico;
    }

    public Topico editarTopicoPorId(int idTopico,  TopicoDatosPeticion topicoEditar) {
        Topico topico = topicoRepository.findById(idTopico).orElse(null);

        if(topico == null){
            throw new ParametroBusquedaInexistenteException("id", "Este topico no existe");
        }


        if(!topicoEditar.autor().equals(topico.getAutor().getId())){
            Optional<Usuario> autor = usuarioRepository.findById(topicoEditar.autor());
            if (autor.isEmpty()){
                throw new ParametroBusquedaInexistenteException("autor", "Este autor no existe");
            }
            topico.setAutor(autor.get());
        }


        if(!topicoEditar.curso().equals(topico.getCurso().getId())){
            Optional<Curso> curso = cursoRepository.findById(topicoEditar.curso());
            if (curso.isEmpty()){
                throw new ParametroBusquedaInexistenteException("curso", "Este curso no existe");
            }
            topico.setCurso(curso.get());
        }

        topico.setMensaje(topicoEditar.mensaje());
        topico.setTitulo(topicoEditar.titulo());

        return topico;
    }

    public void eliminarTopicoPorId(int idTopico) {
        Optional<Topico> topico = topicoRepository.findById(idTopico);

        if(topico.isEmpty()){
            throw new ParametroBusquedaInexistenteException("id", "Este topico no existe");
        }

        topicoRepository.deleteById(idTopico);
    }
}
