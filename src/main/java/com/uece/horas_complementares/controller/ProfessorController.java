package com.uece.horas_complementares.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/professores") // Classe que controla as rotas relacionadas ao professor

public class ProfessorController {
    
    @Autowired
    private ProfessorService professorService; //falta implementar

    @GetMapping
    public List<ProfessorController> listar() {
        return professorService.listar();
    }

    @GetMapping("/{id}")
    public ProfessorController buscar(@PathVariable Long id) {
        return professorService.buscar(id);
    }

    @GetMapping("/{id}/eventos")
    public List<Evento> listarEventos(@PathVariable Long id) {
        return professorService.listarEventos(id);
    }

    @GetMapping("/{id}/confirmarEmail/{token}")
    public void confirmarEmail(@PathVariable Long id, @PathVariable String token) {
        professorService.confirmarEmail(id, token);
    }

    @PostMapping
    public ProfessorController criar(@RequestBody ProfessorController professor) {
        return professorService.criar(professor);
    }
    
    @PostMapping("/login")
    public ProfessorController login(@RequestBody ProfessorController professor) {
        return professorService.login(professor);
    }

    @PostMapping("logout")
    public void logout(@RequestBody ProfessorController professor) {
        professorService.logout(professor);
    }

    @PutMapping("/{id}")
    public ProfessorController atualizar(@PathVariable Long id, @RequestBody ProfessorController professor) {
        return professorService.atualizar(id, professor);
    }

    @PutMapping("/repassarCoordenaria/{id}")
    public void repassarCoordenaria(@PathVariable Long id, @RequestBody ProfessorController professor) {
        professorService.repassarCoordenaria(id, professor);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        professorService.deletar(id);
    }

}
