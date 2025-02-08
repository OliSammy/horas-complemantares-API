package com.uece.horas_complementares.controller;

import com.uece.horas_complementares.service.users.eventos.EventosService;
import org.springframework.web.bind.annotation.RestController;
import com.uece.horas_complementares.model.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;


@RestController
@RequestMapping("/eventos") // Classe que controla as rotas relacionadas ao evento

public class EventosController {
    
    @Autowired
    private EventosService eventosService; 

    @GetMapping
    public List<Evento> listar() {
        return eventosService.listar();
    }

    @GetMapping("/{id}")
    public Evento buscar(@PathVariable Long id) {
        return eventosService.buscar(id);
    }

    @GetMapping("/{id}/inscritos")
    public List<Inscrito> listarInscritos(@PathVariable Long id) {
        return eventosService.listarInscritos(id);
    }

    @GetMapping("/{id}/gerarQRCode")
    public void gerarQRCode(@PathVariable Long id) {
        eventosService.gerarQRCode(id);
    }

    @GetMapping("/{id}/certificados/{alunoId}")
    public void gerarCertificado(@PathVariable Long id, @PathVariable Long alunoId) {
        eventosService.gerarCertificado(id, alunoId);
    }

    @PostMapping
    public Evento criar(@RequestBody Evento evento) {
        return eventosService.criar(evento);
    }

    @PostMapping("/{id}/inscrever")
    public void inscrever(@PathVariable Long id, @RequestBody AlunoController aluno) {
        eventosService.inscrever(id, aluno);
    }

    @PostMapping("/{id}/presença/validar/{alunoId}") //Token irá no header
    public void validarPresenca(@PathVariable Long id, @PathVariable Long alunoId) {//atribui para um aluno
        eventosService.validarPresenca(id, alunoId);
    }

    @PostMapping("/{id}/presença") //Token do professor irá no header 
    public void marcarPresenca(@PathVariable Long id, @RequestBody AlunoController aluno) {//atribui para um conjunto de alunos
        eventosService.marcarPresenca(id, aluno);
    }

    @PutMapping("/{id}")
    public Evento atualizar(@PathVariable Long id, @RequestBody Evento evento) {
        return eventosService.atualizar(id, evento);
    }
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        eventosService.deletar(id);
    }
}
