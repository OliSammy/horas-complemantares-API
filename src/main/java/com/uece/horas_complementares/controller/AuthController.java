package com.uece.horas_complementares.controller;

import com.uece.horas_complementares.model.DTO.user.AuthenticationDTO;
import com.uece.horas_complementares.model.DTO.user.LoginResponseDTO;
import com.uece.horas_complementares.service.auth.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import com.uece.horas_complementares.security.TokenService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/login")
    public ResponseEntity email(@RequestBody @Valid AuthenticationDTO data){
        LoginResponseDTO loginResponseDTO = null;
        try {
            loginResponseDTO = this.authenticationService.login(data);
        } catch (Exception e) {
            log.error("Erro durante o processamento: {}", e.getMessage(), e);
        }
        return ResponseEntity.ok().body(loginResponseDTO);
    }
}
