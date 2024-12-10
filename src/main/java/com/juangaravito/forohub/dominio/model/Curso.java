package com.juangaravito.forohub.dominio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "curso")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private CursoCategoria categoria;
    @OneToMany(mappedBy = "curso")
    private List<Topico> topicos;

}
