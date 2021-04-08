package br.com.zup.mercadolivre.seguranca;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final AuthenticationService authenticationService;

    public AuthenticationTokenFilter(TokenService tokenService,
                                     AuthenticationService authenticationService) {
        this.tokenService = tokenService;
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        String token = pegarTokenDoCabelho(request);
        if(tokenService.isValid(token)) {
            autenticarUsuario(token);
        }
        chain.doFilter(request, response);
    }


    private String pegarTokenDoCabelho(HttpServletRequest httpServletRequest) {
        String cabecalho = httpServletRequest.getHeader("Authorization");

        if(cabecalho == null || !cabecalho.startsWith("Bearer ") || cabecalho.isEmpty()) {
            return null;
        }

        return cabecalho.substring(7, cabecalho.length());
    }


    private void autenticarUsuario(String token) {
        String loginUsuario = tokenService.getLoginUsuario(token);
        UserDetails userDetails = authenticationService.loadUserByUsername(loginUsuario);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
