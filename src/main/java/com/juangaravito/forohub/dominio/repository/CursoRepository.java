package com.juangaravito.forohub.dominio.repository;

import com.juangaravito.forohub.dominio.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
