package com.juangaravito.forohub.dominio.repository;

import com.juangaravito.forohub.dominio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    UserDetails findByCorreo(String username);
}
