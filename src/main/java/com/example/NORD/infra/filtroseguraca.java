package com.example.NORD.infra;

import com.example.NORD.Repositorio.Repositorio;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class filtroseguraca extends OncePerRequestFilter {
    private final TokenServico tokenServico;
    private final Repositorio repositorio;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.recoverToken(request);
        if (token != null ){
            String email = tokenServico.validacao(token);
            UserDetails usuario = repositorio.findByemail(email);


            UsernamePasswordAuthenticationToken autenticar = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(autenticar);
        }
        filterChain.doFilter(request,response);
    }

    private String recoverToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null){
            return null;
        }
        return authHeader.replace("Bearer ", "");

    }
}
