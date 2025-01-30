package com.uece.horas_complementares.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/alunos") // Classe que controla as rotas relacionadas ao aluno

public class Aluno {

    @Autowired
    private AlunoService alunoService; //falta implementar

    @GetMapping
    public List<Aluno> listar() {
        return alunoService.listar();
    }

    @GetMapping("/{id}")
    public Aluno buscar(@PathVariable Long id) {
        return alunoService.buscar(id);
    }

    @GetMapping("/{id}/eventos")
    public List<Evento> listarEventos(@PathVariable Long id) {
        return alunoService.listarEventos(id);
    }

    @GetMapping("/{id}/confirmarEmail/{token}")
    public void confirmarEmail(@PathVariable Long id, @PathVariable String token) {
        alunoService.confirmarEmail(id, token);
    }

    @PostMapping
    public Aluno criar(@RequestBody Aluno aluno) {
        return alunoService.criar(aluno);
    }

    @PostMapping("/login")
    public Aluno login(@RequestBody Aluno aluno) {
        return alunoService.login(aluno);
    }

    @PostMapping("logout")
    public void logout(@RequestBody Aluno aluno) {
        alunoService.logout(aluno);
    }

    @PutMapping("/{id}")
    public Aluno atualizar(@PathVariable Long id, @RequestBody Aluno aluno) {
        return alunoService.atualizar(id, aluno);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        alunoService.deletar(id);
    }
}
