package com.wjnovoam.app.security;

import com.wjnovoam.app.exceptions.BlogAppException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    //Va a estar la clase donde vamos a generar el token, vamos a obtener los clean, a validar el token
    @Value("${app.jwt-secret}") // -> Obtener valor de una propiedad
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication){
        String username = authentication.getName(); //Obtener username del usuario
        Date dateCurrent = new Date(); //Fecha actual
        Date dateExpiration = new Date(dateCurrent.getTime() + jwtExpirationInMs); //fecha de expiracion

        //Establecer lo anterior al token
        return Jwts.builder().setSubject(username)
                .setIssuedAt(new Date()).setExpiration(dateExpiration)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    //Obtener el USERNAME DEL JWT
    public String getUsernameOfJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        return claims.getSubject();
    }


    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            throw  new BlogAppException(HttpStatus.BAD_REQUEST, "Firma JWT no valida");
        }catch (MalformedJwtException ex){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT no valida");
        }catch (ExpiredJwtException ex){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT caducado");
        }catch (UnsupportedJwtException ex){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT no compatible");
        }catch (IllegalArgumentException ex){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "La cadena claims JWT esta vacia");
        }
    }

}
