package com.uece.horas_complementares.controller;

import com.uece.horas_complementares.model.DTO.user.AuthenticationDTO;
import com.uece.horas_complementares.model.DTO.user.LoginResponseDTO;
import com.uece.horas_complementares.service.auth.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.uece.horas_complementares.security.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@SecurityRequirement(name = "BearerAuth")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/login")
    public ResponseEntity email(@RequestBody @Valid AuthenticationDTO data){
        LoginResponseDTO loginResponseDTO = this.authenticationService.login(data);
        return ResponseEntity.ok().body(loginResponseDTO);
    }
}
