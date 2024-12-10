package com.juangaravito.forohub.dominio.model;

import com.juangaravito.forohub.dominio.DTO.TopicoDatosPeticion;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Table(name = "topico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "mensaje")
    private String mensaje;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusTopico status;
    @ManyToOne
    @JoinColumn(name = "autor")
    private Usuario autor;
    @ManyToOne
    @JoinColumn(name = "curso")
    private Curso curso;

    public Topico(TopicoDatosPeticion topico, Usuario autor, Curso curso) {
        this.titulo = topico.titulo();
        this.mensaje = topico.mensaje();
        this.fechaCreacion =  ZonedDateTime.now(ZoneId.of("America/Bogota")).toLocalDateTime();
        this.status = StatusTopico.visible;
        this.autor = autor;
        this.curso = curso;
    }
}
