package com.uece.horas_complementares.controller;

import com.uece.horas_complementares.service.EventoService;
import org.springframework.web.bind.annotation.RestController;
import com.uece.horas_complementares.model.Evento;
import com.uece.horas_complementares.model.DTO.user.AlunoInscritoDTO;
import com.uece.horas_complementares.model.DTO.user.ProfessorEventoDTO;
import com.uece.horas_complementares.model.user.Aluno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private EventoService eventosService; 

    @GetMapping
    public List<Evento> listar() {
        return eventosService.listar();
    }

    @GetMapping("/{id}")
    public Evento buscar(@PathVariable Long id) {
        return eventosService.buscar(id);
    }

    // @GetMapping("/{eventoId}/professores")
    // @PreAuthorize("hasRole('ADMIN') or hasRole('PROFESSOR')")
    // public ResponseEntity<List<ProfessorEventoDTO>> getProfessores(@PathVariable Long eventoId) {
    //     return ResponseEntity.ok(eventosService.getProfessores(eventoId));
    // }


    @PostMapping
    public Evento criar(@RequestBody Evento evento) {
        return eventosService.criar(evento);
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
