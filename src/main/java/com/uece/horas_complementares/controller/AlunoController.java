package com.uece.horas_complementares.controller;

import com.uece.horas_complementares.model.Evento;
import com.uece.horas_complementares.model.user.Aluno;
import com.uece.horas_complementares.service.inscricaoService.InscricaoService;
import com.uece.horas_complementares.service.users.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

public class AlunoController {

    @Autowired
    private AlunoService alunoService; 

    @Autowired
    private InscricaoService inscricaoService;

    @GetMapping
    public List<Aluno> listar() {
        return alunoService.listar();
    }

    @GetMapping("/{id}")
    public Aluno buscar(@PathVariable Long id) {
        return alunoService.buscar(id);
    }

    @GetMapping("/{id}/eventos")
    public ResponseEntity<List<Evento>> listarEventosPorAluno(@PathVariable Long id) {
        return ResponseEntity.ok(inscricaoService.listarEventosPorAluno(id));
    }
    @GetMapping("/{id}/eventos/disponiveis")
    public ResponseEntity<List<Evento>> listarEventosDisponiveis(@PathVariable Long id) {
        return ResponseEntity.ok(inscricaoService.listarEventosNaoInscrito(id));
    }


    @PostMapping
    public Aluno criar(@RequestBody Aluno aluno) {
        return alunoService.criar(aluno);
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
