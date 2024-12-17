package com.juangaravito.forohub.infraestrucutra.Security;

import com.juangaravito.forohub.dominio.model.Usuario;
import com.juangaravito.forohub.dominio.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Security;

@Component
public class FilterSecurityAuthenticated extends OncePerRequestFilter {

    private JWTService jwtService;
    private UsuarioRepository usuarioRepository;

    public FilterSecurityAuthenticated(JWTService jwtService, UsuarioRepository usuarioRepository) {
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header != null){
            String token = header.replace("Bearer ", "");
            String correoUsuario = jwtService.getSubject(token);
            if (correoUsuario != null){
                UserDetails usuario = usuarioRepository.findByCorreo(correoUsuario);
                Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
