package com.uece.horas_complementares.controller;


import com.uece.horas_complementares.model.DTO.user.EventoDTO;
import com.uece.horas_complementares.model.Inscricao;
import com.uece.horas_complementares.model.Presenca;
import com.uece.horas_complementares.model.repository.AlunoRepository;
import com.uece.horas_complementares.model.repository.EventoRepository;
import com.uece.horas_complementares.model.repository.InscricaoRepository;
import com.uece.horas_complementares.model.user.Aluno;
import com.uece.horas_complementares.model.user.Professor;
import com.uece.horas_complementares.model.user.User;
import com.uece.horas_complementares.security.TokenService;
import com.uece.horas_complementares.service.evento.EventoService;
import com.uece.horas_complementares.service.qrCode.qrCodeService;

import jakarta.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.zxing.WriterException;
import com.uece.horas_complementares.model.Evento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private qrCodeService qrCodeService;

    @GetMapping
    public List<Evento> listar() {
        return eventosService.listar();
    }

    @GetMapping("/{id}")
    public Evento buscar(@PathVariable Long id) {
        return eventosService.buscar(id);
    }

    @GetMapping("/alunos/{id}")
    public ResponseEntity<?> listarEventosPorAluno(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @PathVariable Long id) {
        String jwtToken = authorizationHeader.substring(7);
        this.tokenService.validateToken(jwtToken);
        List<Evento> eventos = eventosService.getEventosByAlunoMatricula(id);
        return ResponseEntity.ok().body(eventos);
    }

    @GetMapping("/alunos/{id}/disponiveis")
    public ResponseEntity<?> listarEventosDisponiveis(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @PathVariable Long id) {
        String jwtToken = authorizationHeader.substring(7);
        this.tokenService.validateToken(jwtToken);
        List<Evento> eventos = eventosService.getEventosDisponiveis(id);
        return ResponseEntity.ok().body(eventos);
    }

    @GetMapping("/professor/{id}")
    public ResponseEntity<?> listarEventosProfessor(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @PathVariable Long id) {
        String jwtToken = authorizationHeader.substring(7);
        this.tokenService.validateToken(jwtToken);
        List<Evento> eventos = eventosService.getEventosProfessor(id);
        return ResponseEntity.ok().body(eventos);
    }

    @GetMapping("/qrcode/{idEvento}/{matriculaAluno}")
    public ResponseEntity<?> receberQrCode(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @PathVariable Long idEvento, @PathVariable Long matriculaAluno) {
        String jwtToken = authorizationHeader.substring(7);
        this.tokenService.validateToken(jwtToken);
        String url = "";
        try {
            url = qrCodeService.gerarQRCode(idEvento, matriculaAluno);
        } catch (WriterException | IOException e) {
            return ResponseEntity.status(500).body("Error generating QR code: " + e.getMessage());
        }
        return ResponseEntity.ok().body(url);
    }
    @PostMapping("/presenca")
    public ResponseEntity<?> confirmarPresenca(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestHeader String eventToken) {
            System.out.println("Token: " + eventToken);
            String jwtToken = authorizationHeader.substring(7);
            this.tokenService.validateToken(jwtToken);
            String tokenevent = tokenService.validateEventToken(eventToken);
          
            

                return ResponseEntity.ok().body("Presença confirmada com sucesso!");
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> criar(
            @RequestPart("evento") EventoDTO evento,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @RequestPart("file") MultipartFile file) {
        System.out.println("Evento: " + evento);
        String jwtToken = authorizationHeader.substring(7);
        this.tokenService.validateToken(jwtToken);
        Professor usuario = (Professor) this.tokenService.getUserFromToken(jwtToken);
        Evento newEvento = eventosService.criar(evento, usuario,file);
        return ResponseEntity.ok().body(newEvento);
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
