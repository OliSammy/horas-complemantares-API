package com.uece.horas_complementares.controller;

import com.uece.horas_complementares.model.user.RegisterForm;
import com.uece.horas_complementares.model.user.User;
import com.uece.horas_complementares.security.TokenService;
import com.uece.horas_complementares.service.register.RegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registro")
public class RegistrationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RegisterService registerService;

    @PostMapping("/criar")
    public ResponseEntity register(@RequestBody @Valid RegisterForm data,@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws Exception {
        String jwtToken = authorizationHeader.substring(7);
        this.tokenService.validateToken(jwtToken);
        String idUserEnroller = this.tokenService.getUserFromToken(jwtToken).getNome();
        User user = this.registerService.create(data);
        return ResponseEntity.ok().body(user);
    }
}
