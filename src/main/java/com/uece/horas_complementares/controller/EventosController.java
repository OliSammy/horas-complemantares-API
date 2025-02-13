package com.uece.horas_complementares.controller;

import com.uece.horas_complementares.model.DTO.user.EventoDTO;
import com.uece.horas_complementares.model.Inscricao;
import com.uece.horas_complementares.model.repository.AlunoRepository;
import com.uece.horas_complementares.model.repository.EventoRepository;
import com.uece.horas_complementares.model.repository.InscricaoRepository;
import com.uece.horas_complementares.model.user.Aluno;
import com.uece.horas_complementares.model.user.Professor;
import com.uece.horas_complementares.model.user.User;
import com.uece.horas_complementares.security.TokenService;
import com.uece.horas_complementares.service.evento.EventoService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import com.uece.horas_complementares.model.Evento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/eventos") // Classe que controla as rotas relacionadas ao evento

public class EventosController {
    
    @Autowired
    private EventoService eventosService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private InscricaoRepository inscricaoRepository;

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
    public Evento criar(@RequestBody EventoDTO evento, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String jwtToken = authorizationHeader.substring(7);
        this.tokenService.validateToken(jwtToken);
        Professor usuario = (Professor) this.tokenService.getUserFromToken(jwtToken);
        return eventosService.criar(evento, usuario);
    }


    @PutMapping("/{id}")
    public Evento atualizar(@PathVariable Long id, @RequestBody Evento evento) {
        return eventosService.atualizar(id, evento);
    }
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        eventosService.deletar(id);
    }

    @PutMapping("/inscricao/{idEvento}")
    public ResponseEntity<?> inscreverAluno(@PathVariable Long idEvento, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String jwtToken = authorizationHeader.substring(7);
        this.tokenService.validateToken(jwtToken);
        Aluno usuario = (Aluno) this.tokenService.getUserFromToken(jwtToken);
        System.out.println("Aluno: " + usuario.getNome());

        eventosService.inscreverAluno(idEvento, usuario);
        return ResponseEntity.ok().body(Map.of("msg", "Inscrição realizada com sucesso."));
    }


}
