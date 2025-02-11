package com.uece.horas_complementares.service.users;

import com.uece.horas_complementares.model.user.Aluno;

import java.util.List;
import java.util.stream.Collectors;

import com.uece.horas_complementares.model.Evento;
import com.uece.horas_complementares.model.DTO.user.EventoDTO;
import com.uece.horas_complementares.model.repository.AlunoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }

    public Aluno buscar(Long matricula) {
        return alunoRepository.findByMatricula(matricula).get();
    }

    public Aluno criar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno atualizar(Long matricula, Aluno aluno) {
        Aluno alunoAtualizado = alunoRepository.findByMatricula(matricula).get();
        alunoAtualizado.setNome(aluno.getNome());
        alunoAtualizado.setEmail(aluno.getEmail());
        alunoAtualizado.setSenha(aluno.getSenha());
        alunoAtualizado.setMatricula(aluno.getMatricula());
        return alunoRepository.save(alunoAtualizado);
    }

    public void deletar(Long matricula) {
        alunoRepository.deleteByMatricula(matricula);
    }

    // public List<EventoDTO> listarEventos(Long alunoId) {
    //     Aluno aluno = alunoRepository.findById(alunoId)
    //             .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

    //     List<Evento> eventos = eventoRepository.findByMatriculaAluno(aluno);
    //     return eventos.stream().map(this::toDTO).collect(Collectors.toList());

    // }
    // private EventoDTO toDTO(Evento evento) {
    //     return new EventoDTO(
    //         evento.getId(),
    //         evento.getNome(),
    //         evento.getBanner(),
    //         evento.getTipoHorasComplementares(),
    //         evento.getDataInicial(),
    //         evento.getDataFinal(),
    //         evento.getHorarioInicial(),
    //         evento.getHorarioFinal(),
    //         evento.getLimiteDedescrição(),
    //         evento.getDescricao(),
    //         evento.getIdCurso() != null ? evento.getIdCurso().getId() : null,
    //         evento.getMatriculaProfessor() != null && evento.getMatriculaProfessor().getAluno() != null ? evento.getMatriculaProfessor().getAluno().getMatricula() : null
    //     );
    // }
    // }
}
