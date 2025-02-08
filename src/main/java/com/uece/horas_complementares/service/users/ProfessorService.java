package com.uece.horas_complementares.service.users;

import java.util.List;
import java.util.stream.Collectors;

import com.uece.horas_complementares.model.user.Professor;
import com.uece.horas_complementares.model.Evento;
import com.uece.horas_complementares.model.DTO.user.EventoDTO;
import com.uece.horas_complementares.model.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> listar() {
        return professorRepository.findAll();
    }

    public Professor buscar(Long id) {
        return professorRepository.findById(id).get();
    }
    public Professor criar(Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor atualizar(Long id, Professor professor) {
        Professor professorAtualizado = professorRepository.findById(id).get();
        professorAtualizado.setNome(professor.getNome());
        professorAtualizado.setEmail(professor.getEmail());
        professorAtualizado.setSenha(professor.getSenha());
        professorAtualizado.setMatricula(professor.getMatricula());
        return professorRepository.save(professorAtualizado);
    }
    public void deletar(Long id) {
        professorRepository.deleteById(id);
    }    

    // public List<EventoDTO> listarEventos(Long professorId) {
    //     Professor professor = professorRepository.findById(professorId)
    //             .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        
    //     return eventoRepository.findByMatricula(professor)
    //             .stream()
    //             .map(this::toDTO)
    //             .collect(Collectors.toList());
    // }

//     private EventoDTO toDTO(Evento evento) {
//         return new EventoDTO(
//             evento.getId(),
//             evento.getNome(),
//             evento.getBanner(),
//             evento.getTipoHorasComplementares(),
//             evento.getDataInicial(),
//             evento.getDataFinal(),
//             evento.getHorarioInicial(),
//             evento.getHorarioFinal(),
//             evento.getLimiteDedescrição(),
//             evento.getDescricao(),
//             evento.getIdCurso() != null ? evento.getIdCurso().getId() : null,
//             evento.getMatriculaProfessor() != null ? evento.getMatriculaProfessor().getMatricula() : null
//         );
//     }
 }

