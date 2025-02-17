package com.uece.horas_complementares.service.inscricaoService;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties.Server.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.uece.horas_complementares.model.Evento;
import com.uece.horas_complementares.model.Inscricao;
import com.uece.horas_complementares.model.Presenca;
import com.uece.horas_complementares.model.repository.InscricaoRepository;

@Service
public class InscricaoService {
    @Autowired
    private InscricaoRepository inscricaoRepository;


    public List<Evento> listarEventosPorAluno(Long alunoMatricula) {
        List<Inscricao> inscricoes = inscricaoRepository.findByAlunoMatricula(alunoMatricula);
        List<Evento> eventos = new ArrayList<>();

        for (Inscricao inscricao : inscricoes) {
            eventos.add(inscricao.getEvento()); // Adiciona o evento manualmente
        }
        return eventos;
    }
    public List<Evento> listarEventosNaoInscrito (Long alunoMatricula) {
        List<Evento> eventos = new ArrayList<>();
        List<Evento> eventosNaoInscritos = new ArrayList<>();
        for (Evento evento : eventos) {
            if (!eventos.contains(evento)) {
                eventosNaoInscritos.add(evento);
            }
        }
        return eventosNaoInscritos;
    }
    // public Inscricao buscarInscricaoPorAlunoEvento(Long alunoMatricula, Long eventoId) {
    //     // Specification<Inscricao> spec = new InscricaoByAlunoEvento(alunoMatricula, eventoId);
    //     // return inscricaoRepository.findOne(spec).orElse(null);
    // }
}
