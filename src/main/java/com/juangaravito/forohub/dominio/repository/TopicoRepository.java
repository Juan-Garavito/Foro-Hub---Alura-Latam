package com.juangaravito.forohub.dominio.repository;

import com.juangaravito.forohub.dominio.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Integer> {

    @Query("SELECT " +
            "CASE " +
                "WHEN COUNT(t) > 0 THEN true ELSE false " +
            "END " +
            "FROM Topico t WHERE UPPER(t.titulo) LIKE UPPER(:titulo)")
    boolean existsContaninTitulo(String titulo);


    @Query("SELECT " +
            "CASE " +
            "WHEN COUNT(t) > 0 THEN true ELSE false " +
            "END " +
            "FROM Topico t WHERE UPPER(t.mensaje) LIKE UPPER(:mensaje)")
    boolean existsContainMensaje(String mensaje);

    @Query("SELECT t FROM Topico t " +
            "INNER JOIN Curso c ON c.id = t.curso.id " +
            "WHERE UPPER(c.nombre) LIKE UPPER(:curso)")
    Page<Topico> findAllByNombreCurso(String curso, Pageable pageable);

    @Query("SELECT t FROM Topico t WHERE t.fechaCreacion >= :fechaMinima AND t.fechaCreacion <= :fechaMaxima ")
    Page<Topico> findAllByFecha(LocalDateTime fechaMinima, LocalDateTime fechaMaxima, Pageable pageable);
}
