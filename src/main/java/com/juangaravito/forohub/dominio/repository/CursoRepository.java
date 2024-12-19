package com.juangaravito.forohub.dominio.repository;

import com.juangaravito.forohub.dominio.model.Curso;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
