package com.uece.horas_complementares.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;

@RestController
@RequestMapping("/horas-complementares") // Classe que controla as rotas relacionadas às horas complementares
public class HorasComplementares {
    
    @Autowired
    private HorasComplementaresService horasComplementaresService; //falta implementar

    @GetMapping("/{id}")
    public HorasComplementares buscar(@PathVariable Long id) {
        return horasComplementaresService.buscar(id);
    }

    @GetMapping("/alunos/{alunoId}")
    public List<HorasComplementares> listar(@PathVariable Long alunoId) {
        return horasComplementaresService.listar(alunoId);
    }

    @GetMapping("/alunos/{alunoId}/{alunoId/categoria/passiveisDeAproveitamento}")
    public List<HorasComplementares> listarPassiveisDeAproveitamento(@PathVariable Long alunoId) {
        return horasComplementaresService.listarPassiveisDeAproveitamento(alunoId);
    }

    @GetMapping("/curso/{cursoId}")
    public List<HorasComplementares> listarPorCurso(@PathVariable Long cursoId) {
        return horasComplementaresService.listarPorCurso(cursoId);
    }
    
    @PostMapping
    public HorasComplementares criar(@RequestBody HorasComplementares horasComplementares) {
        return horasComplementaresService.criar(horasComplementares);
    }

    @PutMapping("/{id}")
    public HorasComplementares atualizar(@PathVariable Long id, @RequestBody HorasComplementares horasComplementares) {
        return horasComplementaresService.atualizar(id, horasComplementares);
    }
    
    @DeleteMapping("/{id}")

}
