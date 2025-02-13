package com.uece.horas_complementares.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers("/aluno/**").hasRole("ALUNO")
                        .requestMatchers("/professor/**").hasRole("PROFESSOR")
                        .requestMatchers("/coordenador/**").hasRole("COORDENADOR")
                        .requestMatchers("/registro/criar").permitAll()
                        .requestMatchers("/eventos").hasAnyRole("ALUNO", "PROFESSOR")
                        .requestMatchers(HttpMethod.PUT, "/evento/inscricao/{idEvento}").hasRole("ALUNO")

                        .requestMatchers("/v3/api-docs", "/uploads/**", "/v3/api-docs/**", "/swagger-ui.html",
                                "/swagger-ui/**", "/swagger-resources", "/swagger-resources/**",
                                "/configuration/security", "/configuration/ui", "/webjars/**", "/v2/api-docs")
                        .permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { // Retorna a implementação concreta
        return new BCryptPasswordEncoder();
    }
}
