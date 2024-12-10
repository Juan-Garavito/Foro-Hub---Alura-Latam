package com.juangaravito.forohub.dominio.repository;

import com.juangaravito.forohub.dominio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
