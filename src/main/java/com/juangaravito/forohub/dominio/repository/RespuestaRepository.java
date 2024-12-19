package com.juangaravito.forohub.dominio.repository;

import com.juangaravito.forohub.dominio.model.Respuesta;
import com.juangaravito.forohub.dominio.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Integer> {

    List<Respuesta> findAllByTopico(Topico topico);
}
