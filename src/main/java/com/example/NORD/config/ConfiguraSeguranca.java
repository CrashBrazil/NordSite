package com.example.NORD.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfiguraSeguranca {
    public final ConfiguraFiltroSeguraca filtroseguraca;
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.PUT,"/Nord/Mudarsenha").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE,"/Nord/Deletarconta").hasRole("USER")

                        .requestMatchers(HttpMethod.POST,"/Nord/Salasdocatalogo/{idcatalogo}").permitAll()
                        .requestMatchers(HttpMethod.POST,"/Nord/CriarCatalogo").permitAll()
                        .requestMatchers(HttpMethod.POST, "/Nord/Registrar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/Nord/Login").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(filtroseguraca, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

