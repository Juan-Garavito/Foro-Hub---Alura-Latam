package com.juangaravito.forohub.infraestrucutra.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.juangaravito.forohub.dominio.DTO.Login;
import com.juangaravito.forohub.dominio.DTO.TokenJwt;
import com.juangaravito.forohub.dominio.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Service
public class JWTService {

    @Value("${secret.key.jwt}")
    private String secret_key;

    public TokenJwt crearTokenJWT(Usuario usuario){
        try{
            Algorithm algorithm = Algorithm.HMAC512(secret_key);
            String token = JWT.create()
                    .withIssuer("jg-forohub")
                    .withSubject(usuario.getCorreo())
                    .withExpiresAt(LocalDateTime.now().plusHours(2).atZone(ZoneId.of("America/Bogota")).toInstant())
                    .withClaim("id", usuario.getId())
                    .sign(algorithm);

            return new TokenJwt(token);
        }catch (JWTCreationException exception){
            throw new RuntimeException();
        }

    }

    public String getSubject(String token){

        if(token == null){
            throw new RuntimeException("Token vacio");
        }

        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC512(secret_key);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("jg-forohub")
                    .build();

            decodedJWT = verifier.verify(token);
        } catch (JWTVerificationException exception){
            throw new RuntimeException();
        }

        if(decodedJWT.getSubject() == null){
            throw new RuntimeException("Fallo en el verifier");
        }

        return decodedJWT.getSubject();
    }

}
