package br.com.zup.mercadolivre.seguranca;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.expiration}")
    private String tempoExpiracao;
    @Value("${jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();

        final Date hoje = new Date();
        final Date expiration = new Date(hoje.getTime() + Long.parseLong(tempoExpiracao));
        return Jwts.builder()
                .setIssuer("API Mercado Livre")
                .setSubject(principal.getUsername())
                .setIssuedAt(hoje)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Boolean isValid(String token) {
        try{
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getLoginUsuario(String token) {

        return Jwts.parser()
                .setSigningKey(this.secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
