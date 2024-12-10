package com.juangaravito.forohub.dominio.controller;

import com.juangaravito.forohub.dominio.DTO.TopicoDatosRespuesta;
import com.juangaravito.forohub.dominio.DTO.TopicoDatosPeticion;
import com.juangaravito.forohub.dominio.model.Topico;
import com.juangaravito.forohub.dominio.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {

        this.topicoService = topicoService;
    }

    @PostMapping
    public ResponseEntity<TopicoDatosRespuesta> guardarTopico(@RequestBody @Valid TopicoDatosPeticion topico,
                                                              UriComponentsBuilder uriComponentsBuilder){
        Topico topicoGuardado = topicoService.guardarTopico(topico);
        TopicoDatosRespuesta topicoRespuesta = new TopicoDatosRespuesta(
                topicoGuardado.getId(),
                topicoGuardado.getTitulo(),
                topicoGuardado.getMensaje(),
                topicoGuardado.getAutor().getId(),
                topicoGuardado.getFechaCreacion(),
                topicoGuardado.getCurso().getId());
        URI uri = uriComponentsBuilder.path("topicos/{id}").buildAndExpand(topicoGuardado.getId()).toUri();
        return ResponseEntity.created(uri).body(topicoRespuesta);
    }

    @GetMapping
    public ResponseEntity<PagedModel<TopicoDatosRespuesta>> buscarTodosTopicos(@PageableDefault(sort = "fechaCreacion", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Topico> topicos =  topicoService.buscarTodosTopicos(pageable);
        Page<TopicoDatosRespuesta> topicosRespuesta = topicos.map(topico -> {
            return new TopicoDatosRespuesta(
                    topico.getId(),
                    topico.getTitulo(),
                    topico.getMensaje(),
                    topico.getAutor().getId(),
                    topico.getFechaCreacion(),
                    topico.getCurso().getId());
        });


            return ResponseEntity.ok(new PagedModel<>(topicosRespuesta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDatosRespuesta> buscarTopicoPorId(@PathVariable("id") int idTopico){
        Topico topico =  topicoService.buscarTopicoPorId(idTopico);
        TopicoDatosRespuesta topicoRespuesta = new TopicoDatosRespuesta(
                    topico.getId(),
                    topico.getTitulo(),
                    topico.getMensaje(),
                    topico.getAutor().getId(),
                    topico.getFechaCreacion(),
                    topico.getCurso().getId());


        return ResponseEntity.ok(topicoRespuesta);
    }

    @GetMapping("/curso")
    public ResponseEntity<PagedModel<TopicoDatosRespuesta>> buscarTodosTopicosPorNombreDeCurso(@RequestParam("nombre") String curso, @PageableDefault(sort = "fechaCreacion", direction = Sort.Direction.DESC) Pageable pageable){
        System.out.println("nombre_curso " + curso);
        Page<Topico> topicos =  topicoService.buscarTodosTopicosPorNombreDeCurso(curso, pageable);
        Page<TopicoDatosRespuesta> topicosRespuesta = topicos.map(topico -> {
            return new TopicoDatosRespuesta(
                    topico.getId(),
                    topico.getTitulo(),
                    topico.getMensaje(),
                    topico.getAutor().getId(),
                    topico.getFechaCreacion(),
                    topico.getCurso().getId());
        });


        return ResponseEntity.ok(new PagedModel<>(topicosRespuesta));
    }

    @GetMapping("/fecha")
    public ResponseEntity<PagedModel<TopicoDatosRespuesta>> buscarTodosTopicosPorFecha(@RequestParam("año") int año, @PageableDefault(sort = "fechaCreacion", direction = Sort.Direction.DESC) Pageable pageable){
        System.out.println("fecha " + año);
        Page<Topico> topicos =  topicoService.buscarTodosTopicosPorFecha(año, pageable);
        Page<TopicoDatosRespuesta> topicosRespuesta = topicos.map(topico -> {
            return new TopicoDatosRespuesta(
                    topico.getId(),
                    topico.getTitulo(),
                    topico.getMensaje(),
                    topico.getAutor().getId(),
                    topico.getFechaCreacion(),
                    topico.getCurso().getId());
        });


        return ResponseEntity.ok(new PagedModel<>(topicosRespuesta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoDatosRespuesta> editarTopicoPorId(@PathVariable("id") int idTopico, @RequestBody @Valid TopicoDatosPeticion topicoEditar){
        Topico topicoEditado =  topicoService.editarTopicoPorId(idTopico, topicoEditar);
        TopicoDatosRespuesta topicoRespuesta = new TopicoDatosRespuesta(
                topicoEditado.getId(),
                topicoEditado.getTitulo(),
                topicoEditado.getMensaje(),
                topicoEditado.getAutor().getId(),
                topicoEditado.getFechaCreacion(),
                topicoEditado.getCurso().getId());

        return ResponseEntity.ok(topicoRespuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TopicoDatosRespuesta> eliminarTopicoPorId(@PathVariable("id") int idTopico){
        topicoService.eliminarTopicoPorId(idTopico);
        return ResponseEntity.noContent().build();
    }

}
