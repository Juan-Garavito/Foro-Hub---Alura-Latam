package com.juangaravito.forohub.dominio.controller;

import com.juangaravito.forohub.dominio.DTO.Login;
import com.juangaravito.forohub.dominio.DTO.TokenJwt;
import com.juangaravito.forohub.dominio.model.Usuario;
import com.juangaravito.forohub.infraestrucutra.Security.JWTService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    AuthenticationManager manager;
    JWTService jwtService;

    public LoginController(AuthenticationManager manager, JWTService jwtService) {
        this.manager = manager;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody Login login){
        Authentication authToken = new UsernamePasswordAuthenticationToken(login.getCorreo(), login.getContraseña());
        Authentication usuario = manager.authenticate(authToken);
        TokenJwt tokenJWT = jwtService.crearTokenJWT((Usuario) usuario.getPrincipal());
        return ResponseEntity.ok(tokenJWT);
    }
}
