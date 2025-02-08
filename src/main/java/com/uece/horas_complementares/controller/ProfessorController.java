package com.uece.horas_complementares.controller;

import com.uece.horas_complementares.model.DTO.user.EventoDTO;
import java.util.List;
import com.uece.horas_complementares.model.user.Professor;
import com.uece.horas_complementares.model.Evento;
import com.uece.horas_complementares.service.users.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }


    @GetMapping
    public List<Professor> listar() {
        return professorService.listar();
    }

    @GetMapping("/{id}")
    public Professor buscar(@PathVariable Long id) {
        return professorService.buscar(id);
    }

    @GetMapping("/{id}/eventos")
    public ResponseEntity<List<EventoDTO>> listarEventos(@PathVariable Long id) {
        return ResponseEntity.ok(professorService.listarEventos(id));
    }


    @PostMapping
    public Professor criar(@RequestBody Professor professor) {
        return professorService.criar(professor);
    }
    

    @PutMapping("/{id}")
    public Professor atualizar(@PathVariable Long id, @RequestBody Professor professor) {
        return professorService.atualizar(id, professor);
    }

    // @PutMapping("/repassarCoordenaria/{id}")
    // public void repassarCoordenaria(@PathVariable Long id, @RequestBody Professor professor) {
    //     professorService.repassarCoordenaria(id, professor);
    // }
    //Falta implementar
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        professorService.deletar(id);
    }

}
