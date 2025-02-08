package com.uece.horas_complementares.service;

import java.util.List;
import java.util.stream.Collectors;
import com.uece.horas_complementares.model.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

import com.uece.horas_complementares.model.Evento;

import com.uece.horas_complementares.model.DTO.user.ProfessorEventoDTO;


import com.uece.horas_complementares.model.user.Professor;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;
  
  public List<Evento> listar() {
    return eventoRepository.findAll();
  }
  public Evento buscar(Long id) {
    return eventoRepository.findById(id).get();
  }
  public Evento criar(Evento evento) {
    return eventoRepository.save(evento);
  }
  public Evento atualizar(Long id, Evento evento) {
    Evento eventoAtualizado = eventoRepository.findById(id).get();
    eventoAtualizado.setNome(evento.getNome());
    eventoAtualizado.setBanner(evento.getBanner());
    eventoAtualizado.setTipoHorasComplementares(evento.getTipoHorasComplementares());
    eventoAtualizado.setDataInicial(evento.getDataInicial());
    eventoAtualizado.setDataFinal(evento.getDataFinal());
    return eventoRepository.save(eventoAtualizado);
  }

  public void deletar(Long id) {
    eventoRepository.deleteById(id);
  }
//  public List<AlunoInscritoDTO> listarAlunosInscritos(Long eventoId) {
//         Evento evento = eventoRepository.findById(eventoId)
//                 .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

//         Set<AlunoInscritoDTO> alunosInscritos = evento.getProfessores().stream()
//                 .flatMap(professor -> professor.getAlunos().stream()) // Obtém todos os alunos do professor
//                 .map(this::toAlunoDTO) // Converte Aluno -> AlunoInscritoDTO
//                 .collect(Collectors.toSet());

//         return List.copyOf(alunosInscritos);
//     }

    // public List<ProfessorEventoDTO> getProfessores(Long eventoId) {
    //     Evento evento = eventoRepository.findById(eventoId)
    //             .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

    //     return evento.getProfessor(Long id);
    // }

    // private AlunoInscritoDTO toAlunoDTO(Aluno aluno) {
    //     return new AlunoInscritoDTO(aluno.getMatricula(), aluno.getNome(), aluno.getEmail());
    // }

    private ProfessorEventoDTO toProfessorDTO(Professor professor) {
        return new ProfessorEventoDTO(professor.getMatricula(), professor.getNome(), professor.getEmail());
    }
}


